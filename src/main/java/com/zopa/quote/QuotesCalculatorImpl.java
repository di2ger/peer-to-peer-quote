package com.zopa.quote;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

public class QuotesCalculatorImpl implements QuotesCalculator {

	private static final int PRECISION = 100;

	@Override
	public Quote getQuote(BigDecimal amount, Short termInMonths, List<Offer> offers) {
		Collections.sort(offers);
		BigDecimal remainingAmount = amount;
		BigDecimal collectedRate = new BigDecimal("0");
		for(Offer offer : offers) {
			if (offer.getAvalable().compareTo(remainingAmount) >= 0) {
				collectedRate = collectedRate.add(offer.rate.multiply(remainingAmount));
				remainingAmount = BigDecimal.ZERO;
				break;
			} else {
				collectedRate = collectedRate.add(offer.rate.multiply(offer.getAvalable()));
				remainingAmount = remainingAmount.subtract(offer.getAvalable());
			}
		}
		if (remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
			throw new IllegalArgumentException("It is not possible to provide a quote at that time.");
		}
		// We don't need this precision for calculator, but we might need it for some other uses
		BigDecimal rate = collectedRate.divide(amount, PRECISION, RoundingMode.HALF_UP);
		BigDecimal twelve = new BigDecimal("12");
		// Formula is taken from here: https://www.thecalculatorsite.com/articles/finance/compound-interest-formula.php
		BigDecimal totalRepayment = amount.multiply(BigDecimal.ONE.add(rate.divide(twelve, PRECISION, RoundingMode.HALF_UP)).pow(termInMonths)); 
		BigDecimal monthlyRepayment = totalRepayment.divide(new BigDecimal(termInMonths), PRECISION, RoundingMode.HALF_UP);
		
		return new Quote(amount, rate, monthlyRepayment, totalRepayment);
	}

}
