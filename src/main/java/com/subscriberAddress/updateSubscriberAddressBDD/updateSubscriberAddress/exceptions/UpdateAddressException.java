package com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.exceptions;

public class UpdateAddressException extends RuntimeException {

    private String message;

    public UpdateAddressException(String message) {
        super(message);
    }

}
