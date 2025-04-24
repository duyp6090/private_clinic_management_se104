/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Drug_Unit;
import com.example.demo.domain.Drug_UnitId;

/**
 *
 * @author iset1enloc
 */
@Repository
@EnableJpaRepositories
public interface Drug_Unit_Repository extends  JpaRepository<Drug_Unit, Drug_UnitId>{
    //neccessary query is here
}
