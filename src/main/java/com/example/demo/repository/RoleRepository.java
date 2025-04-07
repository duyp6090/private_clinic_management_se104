/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Doctor;

/**
 *
 * @author iset1enloc
 */
@Repository
public class RoleRepository extends JpaRepository<Doctor, Long>{

}
