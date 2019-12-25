package com.standard.charted.energy.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Sets the price per unit.
 *
 * @param pricePerUnit the new price per unit
 */
@Getter
@Setter
public class RecommandPlan {

	/** The provider and plan. */
	private String providerAndPlan;

	/** The price per unit. */
	private double pricePerUnit;

	/**
	 * Instantiates a new recommand plan.
	 *
	 * @param providerAndPlan the provider and plan
	 * @param pricePerUnit    the price per unit
	 */
	public RecommandPlan(String providerAndPlan, double pricePerUnit) {
		super();
		this.providerAndPlan = providerAndPlan;
		this.pricePerUnit = pricePerUnit;
	}

}
