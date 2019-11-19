package com.nvs.zeisz.nvs.persistence;


import com.nvs.zeisz.nvs.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    Optional<Plan> findByIdentifier(String identifier);
    void deleteByIdentifier(String identifier);
}
