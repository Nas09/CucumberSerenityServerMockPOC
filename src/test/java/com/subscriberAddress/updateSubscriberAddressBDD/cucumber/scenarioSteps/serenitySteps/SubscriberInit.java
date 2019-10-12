package com.subscriberAddress.updateSubscriberAddressBDD.cucumber.scenarioSteps.serenitySteps;

import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.Address;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.Subscriber;
import net.thucydides.core.annotations.Step;

public class SubscriberInit {

    @Step
    public Subscriber newSubscriberAddress(Subscriber subscriber, String active, String country){
        Address address = new Address();
        address.setCountry(country);
        address.setActive(active);
        subscriber.setAddress(address);

        return subscriber;
    }

}
