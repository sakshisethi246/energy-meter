package com.standard.charted.energy.meter.energymeter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

/**
 * Instantiates a new log electric readings.
 */
@Data
@Entity
@Table(name = "LogElectricReadings")
public class LogElectricReadings {

	/** The log electric reading identity. */
	@EmbeddedId
	private LogElectricReadingIdentity logElectricReadingIdentity;

	/** The reading. */
	private double reading;

}
