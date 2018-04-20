package com.zopa.quote;

import java.math.BigDecimal;

import org.checkerframework.checker.nullness.qual.NonNull;


public class Offer implements Comparable<Offer> {
	final String lender;
	final BigDecimal rate;
	final BigDecimal avalable;

	public Offer(String lender, @NonNull BigDecimal rate, @NonNull BigDecimal avalable) {
		this.lender = lender;
		this.rate = rate;
		this.avalable = avalable;
	}
	
	@Override
	public String toString() {
		return "Offer [lender=" + lender + ", rate=" + rate + ", avalable=" + avalable + "]";
	}
	
	@Override
	public int compareTo(Offer o) {
		return this.rate.compareTo(o.getRate());
	}

	public String getLender() {
		return lender;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public BigDecimal getAvalable() {
		return avalable;
	}

}