package com.subscriberAddress.updateSubscriberAddressBDD.cucumber.scenarioSteps.serenitySteps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.subscriberAddress.updateSubscriberAddressBDD.cucumber.serverMocks.ServerResponseExpectations;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.Contract;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.ContractList;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.Subscriber;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.connectors.SubscriberConnector;
import net.thucydides.core.annotations.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class GetttingSubscriberContracts {

    @Autowired
    private SubscriberConnector subscriberConnector;

    @Autowired
    private ServerResponseExpectations serverResponseExpectations;

    @Step
    public List<Contract> getSubscriberContracts(Subscriber subscriber) throws JsonProcessingException {

        ContractList contractList = new ContractList();
        contractList.setContracts(subscriber.getContracts());
        serverResponseExpectations.createExpectationForGettingSubscriberContracts(subscriber, contractList);
        List<Contract> subscrberContracts = subscriberConnector.getSubscriberContracts(subscriber);
        return subscrberContracts;
    }


}
