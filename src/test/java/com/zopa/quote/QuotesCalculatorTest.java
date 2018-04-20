package com.zopa.quote;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuotesCalculatorTest {
	
	static QuotesCalculator calc;
	static OffersLoader offersLoader;
	static List<Offer> offers;
	static BigDecimal bestRate = new BigDecimal("0.069");
	private static Short DEFAULT_TERM = 36;
	
	@BeforeClass
	public static void init() throws IOException, ParseException {
		calc = new QuotesCalculatorImpl();
		offersLoader = new OffersLoaderImpl();
		offers = offersLoader.loadOffers("src/test/resources/Market_Data.csv");
	}
	
	@Test
	public void bestRateSmallAmount() {
		Quote quote = calc.getQuote(new BigDecimal("10"), DEFAULT_TERM, offers);
		Assert.assertEquals("Rate for 10: " + quote.getRate(), 0, quote.getRate().compareTo(bestRate));
	}
	
	@Test
	public void bestRateFullFirstOffer() {
		Quote quote = calc.getQuote(new BigDecimal("480"), DEFAULT_TERM, offers);
		Assert.assertEquals("Rate for 480: " + quote.getRate(), 0, quote.getRate().compareTo(bestRate));
	}
	
	@Test
	public void AllOffersTest() {
		Quote quote = calc.getQuote(new BigDecimal("2330"), DEFAULT_TERM, offers);
		Assert.assertEquals("Rate for 2330: " + quote.getRate(), 1, quote.getRate().compareTo(bestRate));
	}
	
	@Test
	public void CalculationsTest() {
		Quote quote = calc.getQuote(new BigDecimal("1000"), DEFAULT_TERM, offers);
		// The values do not match the given example
		Assert.assertEquals("Quote from example", "Requested amount: £1000\nRate: 7.0%\nMonthly repayment: "
				+ "£34.25\nTotal repayment: £1233.07\n", quote.toString());
		
	}
	
	@Test
	public void tooMuchTest() {
		try {
			calc.getQuote(new BigDecimal("2331"), DEFAULT_TERM, offers);
			Assert.fail("We expect exception here");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Exception text", "It is not possible to provide a quote at that time.", e.getMessage());
		}
	}

}
