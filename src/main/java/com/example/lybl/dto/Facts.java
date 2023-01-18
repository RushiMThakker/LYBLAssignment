package com.example.lybl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Facts {

    @JsonProperty("cibil_score")
    private Integer cibilScore;

    @JsonProperty("credit_cards")
    private Integer creditCards;

    @JsonProperty("existing_loans")
    private ArrayList<Loan> loans;

    @JsonProperty("salary")
    private Integer salary;
}
