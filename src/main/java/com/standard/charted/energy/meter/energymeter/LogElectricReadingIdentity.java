package com.standard.charted.energy.meter.energymeter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Instantiates a new log electric reading identity.
 */
@Data
@Embeddable
public class LogElectricReadingIdentity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@NotNull
	private String id;
	
	/** The time. */
	@NotNull
	private Date time;

}
