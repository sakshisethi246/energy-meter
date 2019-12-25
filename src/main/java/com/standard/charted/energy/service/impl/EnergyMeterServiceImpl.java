package com.standard.charted.energy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.standard.charted.energy.config.CustomConfig;
import com.standard.charted.energy.exception.CustomRuntimeException;
import com.standard.charted.energy.meter.energymeter.LogElectricReadingIdentity;
import com.standard.charted.energy.meter.energymeter.LogElectricReadings;
import com.standard.charted.energy.meter.energymeter.LogReadingRepository;
import com.standard.charted.energy.meter.energymeter.UserRepository;
import com.standard.charted.energy.meter.energymeter.Users;
import com.standard.charted.energy.meter.util.LoadMasterDataUtil;
import com.standard.charted.energy.request.StoreReadingsRequest;
import com.standard.charted.energy.response.ApiResponse;
import com.standard.charted.energy.response.CurrentVsAllResponse;
import com.standard.charted.energy.response.PlanWisePriceInfo;
import com.standard.charted.energy.response.RecommandPlan;
import com.standard.charted.energy.service.EnergyMeterService;

import lombok.extern.slf4j.Slf4j;

/** The Constant log. */
@Slf4j
@Component
public class EnergyMeterServiceImpl implements EnergyMeterService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The log reading repository. */
	@Autowired
	private LogReadingRepository logReadingRepository;

	/** The custom config. */
	@Autowired
	private CustomConfig customConfig;

	@Autowired
	private LoadMasterDataUtil loadMasterDataUtil;

	/**
	 * Store readings.
	 *
	 * @param storeReadingsRequest the store readings request
	 * @return the api response
	 */
	@Override
	@Transactional
	public ApiResponse storeReadings(StoreReadingsRequest storeReadingsRequest) {
		Optional<Users> findById = userRepository.findById(storeReadingsRequest.getSmartMeterId());
		ApiResponse response = null;
		if (findById.isPresent()) {
			log.info("User found with meter id : {}", storeReadingsRequest.getSmartMeterId());
			Users users = findById.get();
			storeReadingsRequest.getElectricityReadings().stream().forEach(readingInfo -> {
				LogElectricReadings logElectricReadings = new LogElectricReadings();
				LogElectricReadingIdentity logElectricReadingIdentity = new LogElectricReadingIdentity();
				logElectricReadingIdentity.setId(users.getSmartMeterId());
				logElectricReadingIdentity.setTime(readingInfo.getTime());
				logElectricReadings.setReading(readingInfo.getReading());
				logElectricReadings.setLogElectricReadingIdentity(logElectricReadingIdentity);
				logReadingRepository.save(logElectricReadings);
				log.info("Log info saved : {}", logElectricReadings);
			});

			response = new ApiResponse();
			response.setCode("200");
			response.setData(null);
			response.setStatus("Success");
		} else {
			log.error("No user found");
			throw new CustomRuntimeException("ERR-001", "Inavlid Smart meter code", null);
		}
		return response;
	}

	/**
	 * Gets the all stored readings.
	 *
	 * @return the all stored readings
	 */
	@Override
	public ApiResponse getAllStoredReadings() {
		log.info("Entering into getAllStoredReading method.");
		ApiResponse apiResponse = new ApiResponse();
		List<LogElectricReadings> readings = this.logReadingRepository.findAll();
		apiResponse.setStatus("SUCCESS");
		apiResponse.setData(readings);
		apiResponse.setCode("200");
		log.info("Exiting from getAllStoredReading method.");
		return apiResponse;
	}

	/**
	 * Gets the current vs plan info.
	 *
	 * @return the current vs plan info
	 */
	@Override
	public ApiResponse getCurrentVsPlanInfo() {
		log.info("Entering into getCurrentVsPlanInfo method.");
		List<Users> allUserInfo = this.userRepository.findAll();
		List<CurrentVsAllResponse> dataAsResp = new ArrayList<CurrentVsAllResponse>();
		allUserInfo.forEach(user -> {
			CurrentVsAllResponse currentVsAllResponse = new CurrentVsAllResponse();
			currentVsAllResponse.setUserName(user.getUserName());
			currentVsAllResponse.setCurrentPlanId(user.getPricePlanId());
			currentVsAllResponse.setCurrentProvider(user.getElectricityProviders().getSupplierName());

			List<PlanWisePriceInfo> list = customConfig.getPriceMap().entrySet().stream()
					.sorted(Map.Entry.comparingByKey()).map(da -> new PlanWisePriceInfo(da.getKey(), da.getValue()))
					.collect(Collectors.toList());
			currentVsAllResponse.setPricePlanComparisions(list);
			dataAsResp.add(currentVsAllResponse);

		});

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus("SUCCESS");
		apiResponse.setData(dataAsResp);
		apiResponse.setCode("200");
		log.info("Exiting from getCurrentVsPlanInfo method.");
		return apiResponse;
	}

	/**
	 * Recommand plan.
	 *
	 * @return the api response
	 */
	@Override
	public ApiResponse recommandPlan() {
		log.info("Entering into recommandedPlan method");
		log.info("=======LOGGING ALL PRICE INFO ========================");
		loadMasterDataUtil.logPricePlanInfo();
		log.info("======================================================");
		List<RecommandPlan> recommandedPlan = customConfig.getPriceMap().entrySet().stream()
				.sorted(Map.Entry.comparingByValue()).map(da -> new RecommandPlan(da.getKey(), da.getValue()))
				.collect(Collectors.toList());
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus("SUCCESS");
		apiResponse.setData(recommandedPlan.get(0));
		apiResponse.setCode("200");
		log.info("Exiting from recommandPlan Method.");
		return apiResponse;

	}

}
