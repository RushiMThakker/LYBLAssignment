package com.example.lybl.service;

import com.example.lybl.dto.Facts;
import com.example.lybl.dto.Loan;

import java.util.ArrayList;

public interface ILoanService {
    ArrayList<Loan> getEligibleLoans(Facts facts);
}
