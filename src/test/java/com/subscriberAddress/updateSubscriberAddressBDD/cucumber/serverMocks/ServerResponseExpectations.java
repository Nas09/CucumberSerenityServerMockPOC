package com.subscriberAddress.updateSubscriberAddressBDD.cucumber.serverMocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.*;

public interface ServerResponseExpectations {

    public void createExpectationForGettingSubscriberContracts(Subscriber subscriber, ContractList contracts) throws JsonProcessingException;
    public void createExpectationForGettingSubscriberMovement(Subscriber subscriber, UpdateAddressMouvement updateAddressMouvement) throws JsonProcessingException;
    public void createExpectationForLogInAdvisor(Advisor advisor, String channel) throws JsonProcessingException;
    public void createExpectationForUpdateSubscriberAddress(Subscriber subscriber, Advisor advisor, Address address) throws JsonProcessingException;
    public void createExpectationForUpdateSubscriberAddressMissingDate(Subscriber subscriber, Advisor advisor, Address address) throws JsonProcessingException;
    public void createExpectationForUpdateSubscriberAddressAdvisorNotConnected(Subscriber subscriber, Advisor advisor, Address address) throws JsonProcessingException;
    public void createExpectationForUpdateSubscriberAddressNotLivingInFrance(Subscriber subscriber, Advisor advisor, Address address) throws JsonProcessingException;
}
