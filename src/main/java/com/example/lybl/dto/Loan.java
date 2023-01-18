package com.example.lybl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Loan {

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("interest")
    private Float interest;

    @JsonProperty("tenure")
    private Integer tenure;

    @JsonProperty("EMI")
    private Float EMI;


    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    public Float getInterest() {
        return interest;
    }

    public void setInterest(Float interest) {
        this.interest = interest;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Float getEMI() {
        return EMI;
    }

    public void setEMI(Float EMI) {
        this.EMI = EMI;
    }
}
