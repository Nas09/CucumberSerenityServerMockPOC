package com.subscriberAddress.updateSubscriberAddressBDD.cucumber.serverMocks.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subscriberAddress.updateSubscriberAddressBDD.cucumber.serverMocks.ServerResponseExpectations;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.*;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Header;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@Component
public class ServerResponseExpectationsImpl implements ServerResponseExpectations {

    @Override
    public void createExpectationForGettingSubscriberContracts(Subscriber subscriber, ContractList contracts) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        new MockServerClient("localhost", 8080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/getsubscriber/contracts")
                                .withHeaders(
                                        new Header("Subscriber-Id", String.valueOf(subscriber.getId()))
                                ),
                        exactly(1)
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody(objectMapper.writeValueAsString(contracts))
                                .withDelay(TimeUnit.SECONDS, 1)
                );
    }

    @Override
    public void createExpectationForGettingSubscriberMovement(Subscriber subscriber, UpdateAddressMouvement updateAddressMouvement) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        new MockServerClient("localhost", 8080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/getsubscriber/updatemouvement")
                                .withHeaders(
                                        new Header("Subscriber-Id", String.valueOf(subscriber.getId()))
                                ),
                        exactly(1)
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody(objectMapper.writeValueAsString(updateAddressMouvement))
                                .withDelay(TimeUnit.SECONDS, 1)
                );
    }

    @Override
    public void createExpectationForLogInAdvisor(Advisor advisor, String channel) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        new MockServerClient("localhost", 8080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/loginadvisor")
                                .withHeaders(
                                        new Header("Advisor-Id", String.valueOf(advisor.getId())),
                                        new Header("Channel", channel)
                                ),
                        exactly(1)
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody(objectMapper.writeValueAsString(advisor))
                                .withDelay(TimeUnit.SECONDS, 1)
                );
    }

    @Override
    public void createExpectationForUpdateSubscriberAddress(Subscriber subscriber, Advisor advisor, Address address) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        new MockServerClient("localhost", 8080)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/updatesubscriberaddress")
                                .withHeaders(
                                        new Header("Content-Type", MediaType.APPLICATION_JSON.toString()),
                                        new Header("Subscriber-Id", String.valueOf(subscriber.getId())),
                                        new Header("Advisor-Id", String.valueOf(advisor.getId()))
                                )
                                .withBody(address.getAddress()),
                        exactly(1)
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody(objectMapper.writeValueAsString(subscriber))
                                .withDelay(TimeUnit.SECONDS, 1)
                );
    }

    @Override
    public void createExpectationForUpdateSubscriberAddressMissingDate(Subscriber subscriber, Advisor advisor, Address address) throws JsonProcessingException {
        new MockServerClient("localhost", 8080)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/updatesubscriberaddress")
                                .withHeaders(
                                        new Header("Content-Type", MediaType.APPLICATION_JSON.toString()),
                                        new Header("Subscriber-Id", String.valueOf(subscriber.getId())),
                                        new Header("Advisor-Id", String.valueOf(advisor.getId()))
                                )
                                .withBody(address.getAddress()),
                        exactly(1)
                )
                .respond(
                        response()
                                .withStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody("La date effective est obligatoire pour modifier de l'adresse de cet abonné")
                                .withDelay(TimeUnit.SECONDS, 1)
                );
    }

    @Override
    public void createExpectationForUpdateSubscriberAddressAdvisorNotConnected(Subscriber subscriber, Advisor advisor, Address address) throws JsonProcessingException {
        new MockServerClient("localhost", 8080)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/updatesubscriberaddress")
                                .withHeaders(
                                        new Header("Content-Type", MediaType.APPLICATION_JSON.toString()),
                                        new Header("Subscriber-Id", String.valueOf(subscriber.getId())),
                                        new Header("Advisor-Id", String.valueOf(advisor.getId()))
                                )
                                .withBody(address.getAddress()),
                        exactly(1)
                )
                .respond(
                        response()
                                .withStatusCode(HttpStatus.FORBIDDEN.value())
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody("Le conseiller n'est pas connecté au canal")
                                .withDelay(TimeUnit.SECONDS, 1)
                );
    }

    @Override
    public void createExpectationForUpdateSubscriberAddressNotLivingInFrance(Subscriber subscriber, Advisor advisor, Address address) throws JsonProcessingException {
        new MockServerClient("localhost", 8080)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/updatesubscriberaddress")
                                .withHeaders(
                                        new Header("Content-Type", MediaType.APPLICATION_JSON.toString()),
                                        new Header("Subscriber-Id", String.valueOf(subscriber.getId())),
                                        new Header("Advisor-Id", String.valueOf(advisor.getId()))
                                )
                                .withBody(address.getAddress()),
                        exactly(1)
                )
                .respond(
                        response()
                                .withStatusCode(HttpStatus.UNAUTHORIZED.value())
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody("L'abonné ne vit pas en France")
                                .withDelay(TimeUnit.SECONDS, 1)
                );
    }
}
