package com.manibhadra.vaccination_center.repo;

import com.manibhadra.vaccination_center.entity.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, Integer> {
}
