package com.standard.charted.energy.meter.energymeter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface LogReadingRepository.
 */
@Repository
public interface LogReadingRepository extends JpaRepository<LogElectricReadings, String> {

}
