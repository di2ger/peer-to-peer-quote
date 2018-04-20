package com.zopa.quote;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface OffersLoader {
	
	List<Offer> loadOffers(String csvFilename) throws IOException, ParseException;

}
