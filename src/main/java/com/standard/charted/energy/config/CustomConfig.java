package com.standard.charted.energy.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class CustomConfig.
 */
@Component
@ConfigurationProperties("entries")

/**
 * Gets the price map.
 *
 * @return the price map
 */
@Getter
@Setter
public class CustomConfig {

	/** The map. */
	private Map<String, String> map;

	/** The price plan map. */
	private Map<Integer, String> pricePlanMap;

	/** The price reading. */
	Supplier<Double> priceReading = () -> new Random().doubles(2, 12).limit(1).findFirst().getAsDouble();

	/** The price map. */
	private Map<String, Double> priceMap = new HashMap<>();

}
