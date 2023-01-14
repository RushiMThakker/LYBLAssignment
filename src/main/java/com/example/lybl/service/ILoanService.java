package com.example.lybl.service;

import com.example.lybl.dto.Facts;
import com.example.lybl.dto.Loan;

public interface ILoanService {
    Loan getEligibleLoans(Facts facts);
}
