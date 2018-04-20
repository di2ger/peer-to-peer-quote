package com.zopa.quote;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
	
	private static final String MARKET_DATA_CSV = "src/test/resources/Market_Data.csv";

	@Test
	public void validAmountTest() {
		Assert.assertEquals(true, App.isInputValid(new String[] {MARKET_DATA_CSV, "1000"}));
		Assert.assertEquals(true, App.isInputValid(new String[] {MARKET_DATA_CSV, "2000"}));
		Assert.assertEquals(true, App.isInputValid(new String[] {MARKET_DATA_CSV, "3000.0"}));
		Assert.assertEquals(true, App.isInputValid(new String[] {MARKET_DATA_CSV, "4000.000"}));
		Assert.assertEquals(true, App.isInputValid(new String[] {MARKET_DATA_CSV, "+5000"}));
		Assert.assertEquals(true, App.isInputValid(new String[] {MARKET_DATA_CSV, "15000"}));
	}
	
	@Test
	public void invalidAmountTest() {
		Assert.assertEquals(false, App.isInputValid(new String[] {MARKET_DATA_CSV, "-1000"}));
		Assert.assertEquals(false, App.isInputValid(new String[] {MARKET_DATA_CSV, "0"}));
		Assert.assertEquals(false, App.isInputValid(new String[] {MARKET_DATA_CSV, "900"}));
		Assert.assertEquals(false, App.isInputValid(new String[] {MARKET_DATA_CSV, "1001"}));
		Assert.assertEquals(false, App.isInputValid(new String[] {MARKET_DATA_CSV, "1000.00000000000001"}));
		Assert.assertEquals(false, App.isInputValid(new String[] {MARKET_DATA_CSV, "16000"}));
		Assert.assertEquals(false, App.isInputValid(new String[] {"no-such-file", "1000"}));
	}

}
