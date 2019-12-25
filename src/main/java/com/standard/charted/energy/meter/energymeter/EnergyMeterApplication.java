package com.standard.charted.energy.meter.energymeter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The Class EnergyMeterApplication.
 */
@SpringBootApplication
@ComponentScan("com.standard.charted")
public class EnergyMeterApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(EnergyMeterApplication.class, args);

	}

}
