package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Diseases;

public interface DiseasesRepository extends JpaRepository<Diseases, Long> {
    // Get all diseases
    public List<Diseases> findAll();

    // Get disease by id
    public Optional<Diseases> findById(Long id);

    // Check exist disease by id
    public boolean existsById(Long id);

    public boolean existsByDiseaseName(String diseaseName);

    // Create and edit disease
    public Diseases save(Diseases disease);

    // Delete disease
    public void deleteById(Long id);
}
