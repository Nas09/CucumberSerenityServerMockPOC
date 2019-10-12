package com.subscriberAddress.updateSubscriberAddressBDD.updateSubscriberAddress.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)
public class ContractList {

    private List<Contract> contracts;

    public ContractList() {
        contracts = new ArrayList<>();
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
