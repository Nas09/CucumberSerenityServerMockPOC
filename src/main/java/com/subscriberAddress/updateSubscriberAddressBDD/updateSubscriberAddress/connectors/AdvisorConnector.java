package com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.connectors;

import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.Advisor;

public interface AdvisorConnector {

    public Advisor advisorChannelLogin(Advisor advisor, String channel);

}
