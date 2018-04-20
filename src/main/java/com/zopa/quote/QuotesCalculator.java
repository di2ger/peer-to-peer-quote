package com.zopa.quote;

import java.math.BigDecimal;
import java.util.List;

public interface QuotesCalculator {

	Quote getQuote(BigDecimal amount, Short termInMonths, List<Offer> offers);

}
