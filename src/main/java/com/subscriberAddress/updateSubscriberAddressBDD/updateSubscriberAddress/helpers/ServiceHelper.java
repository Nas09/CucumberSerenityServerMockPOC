package com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.helpers;

import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.Advisor;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.Subscriber;

public interface ServiceHelper {

    Boolean checkAdvisorConnectedChannel(Advisor advisor, String channel);

    Boolean checkSubscriberCountry(Subscriber subscriber, String country);

    Boolean checkConditionWithEffectiveDate (String condition);

    void updateSubscriberContractsAddress(Subscriber subscriber, String address);


}
