package com.zopa.quote;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class OffersLoaderTest {
	@Test
	public void LoadTest() throws IOException, ParseException {
		OffersLoader loader = new OffersLoaderImpl();
		List<Offer> offers = loader.loadOffers("src/test/resources/Market_Data.csv");
		Assert.assertEquals("Number of offers", 7, offers.size());
		Assert.assertEquals("First offer text",
				"Offer [lender=Bob, rate=0.075, avalable=640]",
				offers.get(0).toString()
				);
	}

}
