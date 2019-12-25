package com.standard.charted.energy.service;

import org.springframework.stereotype.Service;

import com.standard.charted.energy.request.StoreReadingsRequest;
import com.standard.charted.energy.response.ApiResponse;

/**
 * The Interface EnergyMeterService.
 */
@Service
public interface EnergyMeterService {

	/**
	 * Store readings.
	 *
	 * @param storeReadingsRequest the store readings request
	 * @return the api response
	 */
	ApiResponse storeReadings(StoreReadingsRequest storeReadingsRequest);

	/**
	 * Gets the all stored readings.
	 *
	 * @return the all stored readings
	 */
	ApiResponse getAllStoredReadings();

	/**
	 * Gets the current vs plan info.
	 *
	 * @return the current vs plan info
	 */
	ApiResponse getCurrentVsPlanInfo();

	/**
	 * Recommand plan.
	 *
	 * @return the api response
	 */
	ApiResponse recommandPlan();

}
