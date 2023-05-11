package com.haarmk.service.interfaces;

public interface CurrencyService {
	Double getInrPrice();
	Double roundToTwoDecimalPace(Double amount);
	Double usdToInr(Double amount);
	Double getDomainMargin();
}
