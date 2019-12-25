package com.standard.charted.energy.request;

import java.util.Date;

import lombok.Data;

/**
 * Instantiates a new reading info.
 */
@Data
public class ReadingInfo {

	/** The time. */
	private Date time;

	/** The reading. */
	private double reading;
}
