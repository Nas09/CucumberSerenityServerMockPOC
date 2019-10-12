package com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Advisor {

    private long id;
    private String connectedChannel;

    public Advisor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConnectedChannel() {
        return connectedChannel;
    }

    public void setConnectedChannel(String connectedChannel) {
        this.connectedChannel = connectedChannel;
    }


}
