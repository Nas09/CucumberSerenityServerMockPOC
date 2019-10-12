package com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.handlers.exceptionHandlers;

import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.exceptions.AdvisorNotConnectedException;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.exceptions.MissingEffectiveDateForSubscriberException;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.exceptions.SubscriberNotLivingInFranceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.rmi.UnexpectedException;

@Component
public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            if (httpResponse.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
                throw new MissingEffectiveDateForSubscriberException("La date effective est obligatoire pour modifier l'adresse de cet abonné");
            } else if (httpResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new AdvisorNotConnectedException("Le conseiller n'est pas connecté au canal");
            } else if (httpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new SubscriberNotLivingInFranceException("L'abonnée ne vit pas en France");
            }  if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UnexpectedException("");
            }
        } else {
            throw new UnexpectedException("");
        }
    }
}
