package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Diseases;

/**
 *
 * @author iset1enloc
 */
@Repository
@EnableJpaRepositories
public interface DiseaseRepository extends JpaRepository<Diseases, Integer> {
    // You can define custom query methods here if needed
}
