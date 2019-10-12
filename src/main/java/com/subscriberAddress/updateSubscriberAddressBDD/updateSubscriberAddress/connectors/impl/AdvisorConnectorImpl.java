package com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.connectors.impl;

import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.connectors.AdvisorConnector;
import com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities.Advisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AdvisorConnectorImpl implements AdvisorConnector {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Advisor advisorChannelLogin(Advisor advisor, String channel) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Advisor-Id", String.valueOf(advisor.getId()));
        headers.add("Channel", channel);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Advisor> logedAdvisorEntity = restTemplate.exchange("http://localhost:8080/loginadvisor", HttpMethod.GET, request, Advisor.class);

        return logedAdvisorEntity.getBody();
    }
}
