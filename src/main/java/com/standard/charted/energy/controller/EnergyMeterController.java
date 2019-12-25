package com.standard.charted.energy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.standard.charted.energy.config.CustomConfig;
import com.standard.charted.energy.meter.energymeter.ElectricProviderRepository;
import com.standard.charted.energy.meter.energymeter.LogReadingRepository;
import com.standard.charted.energy.meter.energymeter.UserRepository;
import com.standard.charted.energy.request.StoreReadingsRequest;
import com.standard.charted.energy.response.ApiResponse;
import com.standard.charted.energy.service.EnergyMeterService;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class EnergyMeterController.
 */
@RestController

/** The Constant log. */
@Slf4j
public class EnergyMeterController {

	/** The d S service log. */
	@Autowired
	LogReadingRepository dSServiceLog;

	/** The custom config. */
	@Autowired
	CustomConfig customConfig;

	/** The electric provider repository. */
	@Autowired
	ElectricProviderRepository electricProviderRepository;

	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/** The energy meter service. */
	@Autowired
	EnergyMeterService energyMeterService;

	/**
	 * Gets the some test data.
	 *
	 * @param storeReadingsRequest the store readings request
	 * @return the some test data
	 */
	@PostMapping(value = "/store-reading", produces = "application/json")
	public ApiResponse getSomeTestData(@RequestBody StoreReadingsRequest storeReadingsRequest) {
		log.trace("Entering into Store readings endpoint");
		return this.energyMeterService.storeReadings(storeReadingsRequest);
	}

	/**
	 * Gets the stored reading.
	 *
	 * @return the stored reading
	 */
	@GetMapping(value = "/stored-readings", produces = "application/json")
	public ApiResponse getStoredReading() {

		log.trace("entering into get stored readings endpoint.");
		return this.energyMeterService.getAllStoredReadings();

	}

	/**
	 * Current plan vs all plan.
	 *
	 * @return the api response
	 */
	@GetMapping(value = "/current-vs-all-plan", produces = "application/json")
	public ApiResponse currentPlanVsAllPlan() {
		log.trace("entering into current vs all plan info");
		return this.energyMeterService.getCurrentVsPlanInfo();
	}

	/**
	 * Suggest recomannded plan.
	 *
	 * @return the api response
	 */
	@GetMapping(value = "/recommand-plan")
	public ApiResponse suggestRecomanndedPlan() {
		return this.energyMeterService.recommandPlan();
	}

}
