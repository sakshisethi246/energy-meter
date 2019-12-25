package com.standard.charted.energy.meter.energymeter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, String> {

}
