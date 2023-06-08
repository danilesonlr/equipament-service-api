package com.softplan.api.repository;

import com.softplan.api.domain.Equipament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentRepository extends JpaRepository <Equipament, Long> {
}
