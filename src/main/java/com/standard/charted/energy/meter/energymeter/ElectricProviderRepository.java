package com.standard.charted.energy.meter.energymeter;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface ElectricProviderRepository.
 */
public interface ElectricProviderRepository extends JpaRepository<ElectricityProviders, String> {

	/**
	 * Find by supplier name.
	 *
	 * @param supplierName the supplier name
	 * @return the electricity providers
	 */
	public ElectricityProviders findBySupplierName(String supplierName);

}
