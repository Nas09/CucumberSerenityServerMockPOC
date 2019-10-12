# updateSubscriberAddressBDD

SpringBoot BDD/Cucumber + Serenity + MockServer with Maven build

*Tech Stack:
*Maven 3+
*SpringBoot 2+
*Cucumber 4+
*Serenity 2+
*Cucumber with Serinity 1.0.21
*MockServer 5+


This poc contains an implementation of two scenarios Cucumber (a passing scenario and a non-passing scenario).

After a mvn clean install, the following file "updateSubscriberAddressBDD\target\site\serenity\index.html" is generated, it contains the execution report of the scenarios, with Serinity steps details.

You can Debug Cucumber tests by debugin "UpdateSubscriberAddressRunnerTest" class

This POC run a Mock Server on http://localhost:8080, to return expected responses when called by the client.
