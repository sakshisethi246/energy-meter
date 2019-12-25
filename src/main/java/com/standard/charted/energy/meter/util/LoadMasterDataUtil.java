package com.standard.charted.energy.meter.util;

import java.util.Random;
import java.util.function.Supplier;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.standard.charted.energy.config.CustomConfig;
import com.standard.charted.energy.meter.energymeter.ElectricProviderRepository;
import com.standard.charted.energy.meter.energymeter.ElectricityProviders;
import com.standard.charted.energy.meter.energymeter.UserRepository;
import com.standard.charted.energy.meter.energymeter.Users;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class LoadMasterDataUtil.
 */
@Component
@Slf4j
public class LoadMasterDataUtil implements BeanPostProcessor {

	/** The custom config. */
	@Autowired
	private CustomConfig customConfig;

	/** The electric provider repository. */
	@Autowired
	private ElectricProviderRepository electricProviderRepository;

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The price id map. */
	private Supplier<Integer> priceIdMap = () -> new Random().ints(1, 5).limit(1).findFirst().getAsInt();

	/**
	 * Post process after initialization.
	 *
	 * @return the string
	 */
	@PostConstruct
	@Transactional
	public String postProcessAfterInitialization() {
		log.info("Post initialization called");
		customConfig.getMap().forEach((key, value) -> {
			ElectricityProviders electricityProviders = new ElectricityProviders();
			electricityProviders.setPId(key);
			electricityProviders.setSupplierName(value);
			electricProviderRepository.save(electricityProviders);
		});

		preparePriceData();

		loadMasterDataofUser();
		logPricePlanInfo();

		return "ss";
	}

	/**
	 * Load master dataof user.
	 */
	public void loadMasterDataofUser() {
		Users user = new Users();
		ElectricityProviders findBySupplierName = electricProviderRepository
				.findBySupplierName("Dr Evil's Dark Energy");
		user.setElectricityProviders(findBySupplierName);
		user.setUserName("Sarah");
		user.setSmartMeterId("smart-meter-0");
		user.setPricePlanId(customConfig.getPricePlanMap().get(priceIdMap.get()));
		Users user2 = new Users();
		ElectricityProviders provider2 = electricProviderRepository.findBySupplierName("The Green Eco");
		user2.setElectricityProviders(provider2);
		user2.setUserName("Peter");
		user2.setSmartMeterId("smart-meter-1");
		user2.setPricePlanId(customConfig.getPricePlanMap().get(priceIdMap.get()));
		Users user3 = new Users();
		user3.setElectricityProviders(findBySupplierName);
		user3.setSmartMeterId("smart-meter-2");
		user3.setUserName("Charlie");
		user3.setPricePlanId(customConfig.getPricePlanMap().get(priceIdMap.get()));
		Users user4 = new Users();
		ElectricityProviders provider3 = electricProviderRepository.findBySupplierName("Power for Everyone");
		user4.setElectricityProviders(provider3);
		user4.setUserName("Andrea");
		user4.setSmartMeterId("smart-meter-3");
		user4.setPricePlanId(customConfig.getPricePlanMap().get(priceIdMap.get()));
		Users user5 = new Users();
		user5.setElectricityProviders(provider2);
		user5.setUserName("Alex");
		user5.setSmartMeterId("smart-meter-4");
		user5.setPricePlanId(customConfig.getPricePlanMap().get(priceIdMap.get()));
		userRepository.save(user);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		userRepository.save(user5);

	}

	/** The separator. */
	public static String SEPARATOR = "-";

	/**
	 * Prepare price data.
	 */
	public void preparePriceData() {
		String smartMeter = "price-plan-";
		for (int i = 1; i <= 3; i++) {
			for (int j = 0; j < 5; j++) {
				customConfig.getPriceMap().put(customConfig.getMap().get(i + "") + SEPARATOR + smartMeter + j,
						customConfig.getPriceReading().get());
			}
		}
	}

	/**
	 * Log price plan info.
	 */
	public void logPricePlanInfo() {
		int MAXWIDTH = 50;
		customConfig.getPriceMap().forEach((k, v) -> {
			log.info("|--------------------------------------------------------------------------------------------|");
			log.info("\t" + StringUtils.rightPad(k, MAXWIDTH) + "|\t\t" + StringUtils.rightPad(v + "", MAXWIDTH));
		});
		log.info("|--------------------------------------------------------------------------------------------|");
	}

}
