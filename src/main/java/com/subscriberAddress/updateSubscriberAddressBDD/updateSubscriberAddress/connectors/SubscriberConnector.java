package com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.connectors;

import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.*;

import java.util.List;

public interface SubscriberConnector {

    public Subscriber updateSubscriberAddress (Subscriber subscriber, Advisor advisor, Address address);
    public List<Contract> getSubscriberContracts (Subscriber subscriber);
    public UpdateAddressMouvement getSubscriberUpdateAddressMouvement(Subscriber subscriber);
}
