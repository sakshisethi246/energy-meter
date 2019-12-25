package com.standard.charted.energy.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Gets the price plan comparisions.
 *
 * @return the price plan comparisions
 */
@Getter
@Setter
public class CurrentVsAllResponse {
	
	private String userName;

	/** The current provider. */
	private String currentProvider;

	/** The current plan id. */
	private String currentPlanId;

	/** The price plan comparisions. */
	private List<PlanWisePriceInfo> pricePlanComparisions = new ArrayList<PlanWisePriceInfo>();

}
