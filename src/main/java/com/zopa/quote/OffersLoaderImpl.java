package com.zopa.quote;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;

public class OffersLoaderImpl implements OffersLoader {

	@Override
	public List<Offer> loadOffers(String csvFilename) throws IOException, ParseException {
		List<Offer> offers = new ArrayList<>();
		try(CSVReader reader = new CSVReader(new FileReader(csvFilename))) {
			// Skipping the header
			String [] nextLine = reader.readNext();
		    while ((nextLine = reader.readNext()) != null) {
		    	if (nextLine.length != 3) {
		    		throw new IOException("CSV file contains " + nextLine.length + " number of values, expected 3");
		    	}
		    	if (nextLine[1] == null || nextLine[1] == "") {
		    		throw new ParseException("Rate is missing: " + nextLine, 1);
		    	}
		    	if (nextLine[2] == null || nextLine[2] == "") {
		    		throw new ParseException("Available amount is missing: " + nextLine, 2);
		    	}
		    	try {
				    Offer offer = new Offer(
					   nextLine[0],
					   new BigDecimal(nextLine[1]),
					   new BigDecimal(nextLine[2])
				 	   );
				    offers.add(offer);
		    	} catch (NumberFormatException e) {
		    		throw new ParseException("Can't parse numbers: " + nextLine, 0);
		    	}
		    }
		}
		return offers;
	}

}
