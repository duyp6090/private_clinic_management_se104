package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Drugs;
import com.example.demo.dto.drugs.requestDrugs.GetDrugs;

public interface DrugsRepository extends JpaRepository<Drugs, Long> {
    // Get list drugs by filter information
    @Query("""
                SELECT d FROM Drugs d
                WHERE (:#{#filter.drugName} IS NULL OR d.drugName LIKE CONCAT('%', :#{#filter.drugName}, '%'))
                    AND (:#{#filter.minQuantity} IS NULL OR d.quantity >= :#{#filter.minQuantity})
                    AND (:#{#filter.maxQuantity} IS NULL OR d.quantity <= :#{#filter.maxQuantity})
                    AND (:#{#filter.minImportPrice} IS NULL OR d.importPrice >= :#{#filter.minImportPrice})
                    AND (:#{#filter.maxImportPrice} IS NULL OR d.importPrice <= :#{#filter.maxImportPrice})
                    AND (:#{#filter.minExpirationDate} IS NULL OR d.expirationDate >= :#{#filter.minExpirationDate})
                    AND (:#{#filter.maxExpirationDate} IS NULL OR d.expirationDate <= :#{#filter.maxExpirationDate})
                    AND (:#{#filter.unitId} IS NULL OR d.drugsUnit.unitId = :#{#filter.unitId})
            """)
    public Page<Drugs> findAll(@Param("filter") GetDrugs getDrugs, Pageable pageable);

    // Get drug by drugId
    public Optional<Drugs> findByDrugId(long drugId);

    // Get drug by drugName
    public Drugs findByDrugName(String drugName);

    // Save drug
    public Drugs save(Drugs drugs);

    public boolean existsByDrugName(String drugName);

    public boolean existsById(long drugId);

    // Delete drug by drugId
    public void deleteById(long drugId);

}
