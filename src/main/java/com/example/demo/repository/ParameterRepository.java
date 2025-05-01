package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.Parameter;

@Repository
@EnableJpaRepositories
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    // Get all parameters
    public List<Parameter> findAll();

    // Edit parameter
    public Parameter save(Parameter parameter);
}
