package com.subscriberAddress.updateSubscriberAddressBDD.cucumber.scenarioSteps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.subscriberAddress.updateSubscriberAddressBDD.cucumber.helpers.UpdateAddressExpectedException;
import com.subscriberAddress.updateSubscriberAddressBDD.cucumber.scenarioSteps.serenitySteps.*;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.exceptions.UpdateAddressException;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.*;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.mockserver.integration.ClientAndServer;
import org.testng.Assert;
import org.testng.annotations.Ignore;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class UpdateSubscriberAddressSteps {

    @Steps
    private SubscriberInit subscriberInit;
    @Steps
    private UpdateAddressEffectiveDate updateAddressEffectiveDate;
    @Steps
    private GetttingSubscriberContracts getttingSubscriberContracts;
    @Steps
    private GetttingSubscriberMouvement getttingSubscriberMouvement;

    private ClientAndServer mockServer;
    private UpdateAddressExpectedException updateAddressExpectedException;
    private Subscriber subscriber;
    private Address newAddress;

    private void startServer() {
        mockServer = ClientAndServer.startClientAndServer(8080);
    }

    @After
    public void stopServer() {
        mockServer.stop();
    }

    @Before
    public void init() {
        startServer();
        newAddress = new Address();
        newAddress.setAddress("17 rue Franklin Roosevelt");

        List<Contract> contracts = new ArrayList<>();
        Contract contract = new Contract();
        contract.setAddress("1");
        contracts.add(contract);
        contract.setAddress("2");
        contracts.add(contract);
        subscriber = new Subscriber();
        subscriber.setContracts(contracts);

    }

    @Given("un abonné avec une adresse principale ([^\"]*) en ([^\"]*)")
    public void abonneAvecAdresseActiveEnPays(String active, String country) {
        subscriber = subscriberInit.newSubscriberAddress(subscriber, active, country);
    }

    @When("le conseiller connecté à ([^\"]*) modifie l'adresse de l'abonné ([^\"]*)")
    public void conseillerConnecteCanalModifierAbonneCondition(String channel, String effectiveDateCondition) {

        updateAddressExpectedException = new UpdateAddressExpectedException();

        try {
            subscriber = updateAddressEffectiveDate.updateSubscriberAddress(subscriber, channel, newAddress, effectiveDateCondition);
            updateAddressExpectedException.setExpectedException(false);
        } catch (UpdateAddressException | JsonProcessingException e1) {
            updateAddressExpectedException.setExpectedException(true);
        }
    }

    @Then("l'adresse de l'abonné modifiée est enregistrée sur l'ensemble des contrats de l'abonné")
    public void adresseAbonneModifieeEnregistreeSurContrats() throws JsonProcessingException {
        Assert.assertFalse(updateAddressExpectedException.isExpectedException());
        List<Contract> subscriberContracts = getttingSubscriberContracts.getSubscriberContracts(subscriber);
        for (Contract subscriberContract : subscriberContracts) {
            Assert.assertEquals(subscriberContract.getAddress(), newAddress.getAddress());
        }
    }

    @Then("un mouvement de modification d'adresse est créé")
    public void movementModificationAdresseCree() throws JsonProcessingException {
        UpdateAddressMouvement updateAddressMouvement = null;
        updateAddressMouvement = getttingSubscriberMouvement.getSubscriberUpdateMouvement(subscriber);
        Assert.assertNotNull(updateAddressMouvement);
        Assert.assertEquals(updateAddressMouvement.getOldAddress().getAddress(), subscriber.getAddress().getAddress());
        Assert.assertEquals(updateAddressMouvement.getNewAddress().getAddress(), newAddress.getAddress());
    }

}
