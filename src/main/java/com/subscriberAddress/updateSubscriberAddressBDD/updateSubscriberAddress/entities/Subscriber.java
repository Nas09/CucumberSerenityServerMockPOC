package com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscriber {

    private long id;
    private Address address;
    private String channel;
    private String condition;
    private List<Contract> contracts;
    private UpdateAddressMouvement updateAddressMouvement;

    public Subscriber(Subscriber subscriber) {
        this.setId(subscriber.getId());
        this.setAddress(subscriber.getAddress());
        this.setChannel(subscriber.getChannel());
        this.setCondition(subscriber.getCondition());
        this.setContracts(subscriber.getContracts());
        this.setUpdateAddressMouvement(subscriber.getUpdateAddressMouvement());
    }

    public Subscriber() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public UpdateAddressMouvement getUpdateAddressMouvement() {
        return updateAddressMouvement;
    }

    public void setUpdateAddressMouvement(UpdateAddressMouvement updateAddressMouvement) {
        this.updateAddressMouvement = updateAddressMouvement;
    }
}
