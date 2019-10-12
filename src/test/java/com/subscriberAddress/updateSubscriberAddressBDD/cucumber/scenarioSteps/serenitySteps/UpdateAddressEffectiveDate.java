package com.subscriberAddress.updateSubscriberAddressBDD.cucumber.scenarioSteps.serenitySteps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.subscriberAddress.updateSubscriberAddressBDD.cucumber.serverMocks.ServerResponseExpectations;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.helpers.ServiceHelper;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.exceptions.UpdateAddressException;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.*;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.connectors.AdvisorConnector;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.connectors.SubscriberConnector;
import net.thucydides.core.annotations.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class UpdateAddressEffectiveDate {

    @Autowired
    private AdvisorConnector advisorConnector;
    @Autowired
    private SubscriberConnector subscriberConnector;

    @Autowired
    private ServiceHelper serviceHelper;

    @Autowired
    private ServerResponseExpectations serverResponseExpectations;


    public Advisor logInChannel (String channel) throws JsonProcessingException {
        Advisor advisor = new Advisor();
        advisor.setConnectedChannel(channel);
        serverResponseExpectations.createExpectationForLogInAdvisor(advisor, channel);

        Advisor logedAdvisor = advisorConnector.advisorChannelLogin(advisor, channel);

        return logedAdvisor;
    }

    @Step
    public Subscriber updateSubscriberAddress(Subscriber subscriber, String channel, Address address, String effectiveDateCondition) throws UpdateAddressException, JsonProcessingException {

        Advisor advisor = logInChannel(channel);

        subscriber.setCondition(effectiveDateCondition);
        subscriber.setChannel(channel);

        List<Contract> contracts = new ArrayList<>();
        Contract contract = new Contract();
        contract.setAddress("1");
        contracts.add(contract);
        contract.setAddress("2");
        contracts.add(contract);
        subscriber.setContracts(contracts);

        address.setActive(subscriber.getAddress().getCountry());
        address.setCountry(subscriber.getAddress().getCountry());

        Subscriber mockedSubscriber = new Subscriber(subscriber);
        serviceHelper.updateSubscriberContractsAddress(mockedSubscriber, address.getAddress());

        UpdateAddressMouvement updateAddressMouvement = new UpdateAddressMouvement();
        updateAddressMouvement.setOldAddress(mockedSubscriber.getAddress());
        updateAddressMouvement.setNewAddress(address);
        mockedSubscriber.setUpdateAddressMouvement(updateAddressMouvement);


        if (!serviceHelper.checkAdvisorConnectedChannel(advisor,subscriber.getChannel())){
            serverResponseExpectations.createExpectationForUpdateSubscriberAddressAdvisorNotConnected(mockedSubscriber, advisor, address);
        } else if (!serviceHelper.checkSubscriberCountry(subscriber, "France")){
            serverResponseExpectations.createExpectationForUpdateSubscriberAddressNotLivingInFrance(mockedSubscriber, advisor, address);
        } else if (serviceHelper.checkConditionWithEffectiveDate(subscriber.getCondition()) && null == address.getEffectiveDate() ){
            serverResponseExpectations.createExpectationForUpdateSubscriberAddressMissingDate(mockedSubscriber, advisor, address);
        } else {
            serverResponseExpectations.createExpectationForUpdateSubscriberAddress(mockedSubscriber, advisor, address);
        }

        Subscriber updatedSubscriber = subscriberConnector.updateSubscriberAddress(subscriber,advisor,address);
        return updatedSubscriber;
    }



}
