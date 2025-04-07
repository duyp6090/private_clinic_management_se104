/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

/**
 *
 * @author iset1enloc
 */
@Repository
@EnableJpaRepositories
public interface SupporterRepository extends JpaRepository<User, Long> {
    SupporterRepository save(SupporterRepository supporter);
    SupporterRepository update(Integer staffId, SupporterRepository supporter);
}
