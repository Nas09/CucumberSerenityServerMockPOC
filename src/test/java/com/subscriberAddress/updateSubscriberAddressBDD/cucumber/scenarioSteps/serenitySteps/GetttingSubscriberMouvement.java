package com.subscriberAddress.updateSubscriberAddressBDD.cucumber.scenarioSteps.serenitySteps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.subscriberAddress.updateSubscriberAddressBDD.cucumber.serverMocks.ServerResponseExpectations;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.Subscriber;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.UpdateAddressMouvement;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.connectors.SubscriberConnector;
import net.thucydides.core.annotations.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetttingSubscriberMouvement {

    @Autowired
    private SubscriberConnector subscriberConnector;

    @Autowired
    private ServerResponseExpectations serverResponseExpectations;


    @Step
    public UpdateAddressMouvement getSubscriberUpdateMouvement(Subscriber subscriber) throws JsonProcessingException {
        UpdateAddressMouvement updateAddressMouvement = subscriber.getUpdateAddressMouvement();
        serverResponseExpectations.createExpectationForGettingSubscriberMovement(subscriber, updateAddressMouvement);
        UpdateAddressMouvement subcriberUpbdateAddressMouvement = subscriberConnector.getSubscriberUpdateAddressMouvement(subscriber);
        return subcriberUpbdateAddressMouvement;
    }


}
