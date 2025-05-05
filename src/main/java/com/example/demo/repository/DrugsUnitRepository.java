package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.DrugsUnit;
import com.example.demo.dto.drugs.requestDrugs.GetUnitDrug;

@Repository
@EnableJpaRepositories
public interface DrugsUnitRepository extends JpaRepository<DrugsUnit, Long> {
    // get all drugsUnit
    @Query("""
            SELECT du FROM DrugsUnit du
            WHERE (:#{#filter.getUnitName()} IS NULL OR LOWER(du.unitName) LIKE LOWER(CONCAT('%', :#{#filter.getUnitName()}, '%')))
            """)
    public Page<DrugsUnit> findAll(@Param("filter") GetUnitDrug getUnitDrug, Pageable pageable);

    // Create and update drugsUnit
    public boolean existsByUnitName(String unitName);

    public boolean existsById(Long id);

    // Delete drugsUnit
    public void deleteById(Long id);
}
