package com.example.lybl.controller;

import com.example.lybl.dto.Facts;
import com.example.lybl.dto.Loan;
import com.example.lybl.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired private ILoanService loanService;

    /**
     * An API to get loan payable based on input facts
     *
     * @param facts Facts defining the current economics
     * @return an object of class Loan
     */
    @Operation(summary = "Get possible loan offer", description = "Based on input facts, engine decide what could be the payable loan")
    @PostMapping(
            value = "/value",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<Loan> getLoanAmount(
            @Parameter(description = "Facts defining the current economics of person affecting loan amount", example = "credit_cards, existing_loans")
            @RequestBody Facts facts) {
        return loanService.getEligibleLoans(facts);
    }
}
