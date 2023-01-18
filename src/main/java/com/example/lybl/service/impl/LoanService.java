package com.example.lybl.service.impl;

import com.example.lybl.dto.Facts;
import com.example.lybl.dto.Loan;
import com.example.lybl.service.ILoanService;
import com.example.lybl.util.Constants;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@Service
public class LoanService implements ILoanService {

    /**
     * Based on facts it will return a list of various possible loans
     *
     * @param facts - facts of person
     * @return list of possible loans
     */
    @Override
    public ArrayList<Loan> getEligibleLoans(Facts facts) {
        ArrayList<Loan> possibleLoans = new ArrayList<>();

        // Assume person is spending only 60% max of salary
        double savings = 0.4 * facts.getSalary();
        float r = 10.25f; // rate of interest
        float mr = r/12/100;// monthly rate of interest
        double debt = facts.getSalary()-savings;
        double dti = (debt)/facts.getSalary();

        // Loop:
        for (int i = 0; i < Constants.TENURES.size(); ++i) {
            // Vary the tenure
            int tenure = Constants.TENURES.get(i);

            // Find max loan amount

            // Home loan eligibility=monthly savings/equated monthly instalment (EMI) per lakh x 1 lakh
            // Lets assume max loan amount to be = salary/DTI
            // Then apply weight factors to reduce or increase amount
            double maxLoanAmount = (facts.getSalary()/dti)*tenure*12d;

            // Credit cards and loans give negative weight, whereas cibil score > 750 gives positive
            // Credit cards - 2%, each loan - 5%, cibil score - 10 points 1 %
            maxLoanAmount = maxLoanAmount * (1 - 0.05*facts.getLoans().size() - 0.02*facts.getCreditCards() + ((double)facts.getCibilScore()-750)/1000);
            BigDecimal bd = BigDecimal.valueOf(maxLoanAmount).setScale(2, RoundingMode.HALF_EVEN);
            maxLoanAmount = bd.doubleValue();

            // Find EMI
            // EMI = P × r × (1 + r)n/((1 + r)n - 1) where P= Loan amount, r= monthly rate of interest rate, n=tenure in number of months.
            // Looking at below formula, P is 1 lakh, assume r is 10
            double emi = maxLoanAmount * mr * (Math.pow(1+mr, tenure*12d) /(Math.pow(1+mr, (tenure*12)-1d)));
            bd = BigDecimal.valueOf(emi).setScale(2, RoundingMode.HALF_EVEN);
            emi = bd.doubleValue();

            // Create loan objects
            int loanAmount = (int) Math.floor(maxLoanAmount);

            Loan loan = new Loan();
            loan.setTenure(tenure);
            loan.setEmi(emi);
            loan.setAmount(loanAmount);
            loan.setInterest(r);
            possibleLoans.add(loan);
        }

        // Return possible loans
        return possibleLoans;
    }
}
