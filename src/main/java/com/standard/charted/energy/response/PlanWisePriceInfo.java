package com.standard.charted.energy.response;

import lombok.Getter;
import lombok.Setter;


/**
 * Sets the calculated price.
 *
 * @param calculatedPrice the new calculated price
 */
@Getter
@Setter
public class PlanWisePriceInfo {
	
	public PlanWisePriceInfo(String providerPlanId, double calculatedPrice) {
		super();
		this.providerPlanId = providerPlanId;
		this.calculatedPrice = calculatedPrice;
	}
	/** The provider plan id. */
	private String providerPlanId;
	

	/** The calculated price. */
	private double calculatedPrice;
}
