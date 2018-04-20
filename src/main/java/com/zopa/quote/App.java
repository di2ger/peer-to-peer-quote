package com.zopa.quote;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

public class App {
	
	private static final String USAGE = "Usage: quote.sh <market_file> <loan_amount>";
	private static final BigDecimal minLoanAmount = new BigDecimal("1000");
	private static final BigDecimal maxLoanAmount = new BigDecimal("15000");
	
	private static String marketFileName;
	private static BigDecimal loanAmount = BigDecimal.ZERO;
	private static Short DEFAULT_TERM = 36;

	public static void main(String[] args) {
		if(!isInputValid(args)) {
			System.exit(1);
		}
		QuotesCalculator calc = new QuotesCalculatorImpl();
		OffersLoader offersLoader = new OffersLoaderImpl();
		List<Offer> offers = null;
		try {
			offers = offersLoader.loadOffers(marketFileName);
		} catch (IOException | ParseException e) {
			System.out.println("Error loading market file " + marketFileName);
			System.out.println("Description: " + e.getMessage());
			System.exit(1);
		}
		try {
			Quote quote = calc.getQuote(loanAmount, DEFAULT_TERM, offers);
			System.out.println(quote.toString());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			System.exit(1);
		}

	}
	
	public static boolean isInputValid(String[] args) {
		if (args.length != 2) {
			System.out.println(USAGE);
			return false;
		}
		marketFileName = args[0];
		if(!Files.exists(Paths.get(marketFileName))) { 
		    System.out.println("The specified market file is missing: " + marketFileName);
		    return false;
		}
		try {
			loanAmount = new BigDecimal(args[1]);
			
		} catch (NumberFormatException e) {
			System.out.println("Loan amount is not valid number: " + args[1]);
			return false;
		}
		if (loanAmount.compareTo(minLoanAmount) < 0
				|| loanAmount.compareTo(maxLoanAmount) > 0
				|| (loanAmount.remainder(new BigDecimal("100")).compareTo(BigDecimal.ZERO)) != 0) {
			System.out.println("Loan amount (" + loanAmount + ") should be within [" + minLoanAmount + ", " 
				+ maxLoanAmount + "] and divicible by 100.");
			return false;
		}
		return true;
	}

}
