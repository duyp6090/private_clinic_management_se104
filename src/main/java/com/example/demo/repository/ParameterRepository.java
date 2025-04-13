/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Parameter;

/**
 *
 * @author iset1enloc
 */
@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    List<Parameter> findByParamType(String paramType);
    Optional<Parameter> findByParamTypeAndParamKey(String paramType, String paramKey);
}