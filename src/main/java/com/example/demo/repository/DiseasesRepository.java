package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Diseases;
import com.example.demo.dto.diseases.GetDiseasesDTO;

public interface DiseasesRepository extends JpaRepository<Diseases, Long> {
    // Get all diseases
    @Query("""
                SELECT d FROM Diseases d
                WHERE (:#{#filter.getDiseaseName()} IS NULL OR LOWER(d.diseaseName) LIKE LOWER(CONCAT('%', :#{#filter.getDiseaseName()}, '%')))
            """)
    public Page<Diseases> findAll(@Param("filter") GetDiseasesDTO getDiseasesDTO, Pageable pageable);

    // Get disease by id
    public Optional<Diseases> findByDiseaseId(Long id);

    // Check exist disease by id
    public boolean existsByDiseaseId(Long id);

    public boolean existsByDiseaseName(String diseaseName);

    // Create and edit disease
    public Diseases save(Diseases disease);

    // Delete disease
    public void deleteByDiseaseId(Long id);
}
