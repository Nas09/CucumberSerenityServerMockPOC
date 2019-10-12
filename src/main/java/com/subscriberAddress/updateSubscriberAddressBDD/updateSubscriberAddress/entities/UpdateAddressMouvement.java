package com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateAddressMouvement {

    private Address oldAddress;
    private Address newAddress;

    public Address getOldAddress() {
        return oldAddress;
    }

    public void setOldAddress(Address oldAddress) {
        this.oldAddress = oldAddress;
    }

    public Address getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(Address newAddress) {
        this.newAddress = newAddress;
    }
}
