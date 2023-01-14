package com.example.lybl.service.impl;

import com.example.lybl.dto.Facts;
import com.example.lybl.dto.Loan;
import com.example.lybl.service.ILoanService;
import org.springframework.stereotype.Service;

@Service
public class LoanService implements ILoanService {

    @Override
    public Loan getEligibleLoans(Facts facts) {
        // TODO: Add engine rules to get eligible loan
        Loan dummyLoan = new Loan();
        dummyLoan.setEMI(1000.00F);
        dummyLoan.setInterest(10F);
        dummyLoan.setAmount(100000);
        dummyLoan.setTenure(12);
        return dummyLoan;
    }
}
