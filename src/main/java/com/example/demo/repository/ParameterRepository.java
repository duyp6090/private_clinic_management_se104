package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
package com.example.demo.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import io.micrometer.common.lang.NonNull;

@Repository
@EnableJpaRepositories
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    // Get all parameters
    @NonNull
    public List<Parameter> findAll();

    // Edit parameter
    @NonNull
    public Parameter save(Parameter parameter);
}
