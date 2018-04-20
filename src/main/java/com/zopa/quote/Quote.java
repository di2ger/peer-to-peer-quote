package com.zopa.quote;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Quote {
	final BigDecimal requestedAmount;
	final BigDecimal rate;
	final BigDecimal monthlyRepayment;
	final BigDecimal totalRepayment;
	
	DecimalFormat dfAmount = new DecimalFormat("£#");
	DecimalFormat dfRate = new DecimalFormat("#.0%");
	DecimalFormat dfRepayment = new DecimalFormat("£#.00");
	
	public Quote(BigDecimal requestedAmount, BigDecimal rate, BigDecimal monthlyRepayment, BigDecimal totalRepayment) {
		this.requestedAmount = requestedAmount;
		this.rate = rate;
		this.monthlyRepayment = monthlyRepayment;
		this.totalRepayment = totalRepayment;
	}

	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public BigDecimal getMonthlyRepayment() {
		return monthlyRepayment;
	}

	public BigDecimal getTotalRepayment() {
		return totalRepayment;
	}

	@Override
	public String toString() {
		return "Requested amount: " + dfAmount.format(requestedAmount) + "\n"
				+ "Rate: " + dfRate.format(rate) + "\n"
				+ "Monthly repayment: " + dfRepayment.format(monthlyRepayment) + "\n"
				+ "Total repayment: " + dfRepayment.format(totalRepayment) + "\n";
	}
	
	
	
}
