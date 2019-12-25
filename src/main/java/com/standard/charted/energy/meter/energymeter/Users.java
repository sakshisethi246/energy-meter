package com.standard.charted.energy.meter.energymeter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Instantiates a new users.
 */
@Data
@Entity
public class Users {

	/** The smart meter id. */
	@Id
	private String smartMeterId;

	/** The user name. */
	private String userName;

	/** The last name. */
	private String lastName;

	/** The email. */
	private String email;

	/** The electricity providers. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pId")
	private ElectricityProviders electricityProviders;

	/** The price plan id. */
	private String pricePlanId;

}
