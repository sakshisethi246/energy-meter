package com.standard.charted.energy.meter.energymeter;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * The Class ElectricityProviders.
 */
@Entity

/**
 * Instantiates a new electricity providers.
 */
@Data
public class ElectricityProviders {

	/** The p id. */
	@Id
	private String pId;

	/** The supplier name. */
	private String supplierName;

	/** The user set. */
	@OneToMany(mappedBy = "electricityProviders")
	private Set<Users> userSet;

}
