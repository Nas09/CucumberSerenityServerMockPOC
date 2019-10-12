package com.subscriberAddress.updateSubscriberAddressBDD.cucumber;


import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = {"classpath:features/"},
        tags = {"@adresse","@modification","@TestsRecevabilité","@scenarioTest"},
        glue = {"com.subscriberAddress.updateSubscriberAddressBDD.cucumber.scenarioSteps"}
        )
public class UpdateSubscriberAddressRunnerTest {

}
