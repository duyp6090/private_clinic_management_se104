/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.repository;

import java.security.Permission;

import org.springframework.stereotype.Repository;

/**
 *
 * @author iset1enloc
 */

@Repository
public class PermissionRepository extends  JpaRepository<Permission, Long> {

}
