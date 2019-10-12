package com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.helpers.impl;

import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.Advisor;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.Subscriber;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.helpers.ServiceHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ServiceHelperImpl implements ServiceHelper {

    @Override
    public Boolean checkAdvisorConnectedChannel(Advisor advisor, String canal) {
        if (!StringUtils.isEmpty(advisor.getConnectedChannel()) && advisor.getConnectedChannel().equals(canal)) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkSubscriberCountry(Subscriber subscriber, String country) {
        if (!StringUtils.isEmpty(subscriber.getAddress()) && !StringUtils.isEmpty(subscriber.getAddress().getCountry()) && subscriber.getAddress().getCountry().equals("France")) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkConditionWithEffectiveDate(String condition) {
        if (!StringUtils.isEmpty(condition) && condition.equals("avec date d'effet")) {
            return true;
        }
        return false;
    }

    @Override
    public void updateSubscriberContractsAddress(Subscriber subscriber, String address) {
        subscriber.getContracts().forEach(contract -> contract.setAddress(address));
    }

}
