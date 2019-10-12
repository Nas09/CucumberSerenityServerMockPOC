package com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.connectors.impl;

import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.connectors.SubscriberConnector;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.*;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.exceptions.UpdateAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SubscriberConnectorImpl implements SubscriberConnector {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Subscriber updateSubscriberAddress (Subscriber subscriber, Advisor advisor, Address address) throws UpdateAddressException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Subscriber-Id", String.valueOf(subscriber.getId()));
        headers.add("Advisor-Id", String.valueOf(advisor.getId()));
        HttpEntity<String> request = new HttpEntity<String>(address.getAddress(), headers);

        ResponseEntity<Subscriber> response = restTemplate.exchange("http://localhost:8080/updatesubscriberaddress", HttpMethod.POST, request, Subscriber.class);

        return response.getBody();
    }

    @Override
    public List<Contract> getSubscriberContracts (Subscriber subscriber) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Subscriber-Id", String.valueOf(subscriber.getId()));
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<ContractList> response = restTemplate.exchange("http://localhost:8080/getsubscriber/contracts", HttpMethod.GET, request, ContractList.class);

        List<Contract> contracts = response.getBody().getContracts();
        return contracts;
    }

    @Override
    public UpdateAddressMouvement getSubscriberUpdateAddressMouvement(Subscriber subscriber) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Subscriber-Id", String.valueOf(subscriber.getId()));
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<UpdateAddressMouvement> updateAddressMouvement = restTemplate.exchange("http://localhost:8080/getsubscriber/updatemouvement", HttpMethod.GET, request, UpdateAddressMouvement.class);

        return updateAddressMouvement.getBody();
    }
}
