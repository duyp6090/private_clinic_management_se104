/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Permission;

/**
 *
 * @author iset1enloc
 */

@Repository
@EnableJpaRepositories
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    
    @Query("SELECT r.role_name " +
    "FROM Role r " +
    "JOIN Role_Permission rp " +
    "JOIN rp.permission p " +
    "WHERE r.role_id = :roleId")
    List<String> getPermissionsByRoleId(@Param("roleId") int roleId);

    // @Query("SELECT p.permission_name FROM PERMISSION p WHERE p.permission_id =:permissionId")
    // List<String> findPermissionById(@Param("permissionId") int permissionId);

    // @Query("SELECT '*' FROM PERMISSION p WHERE p.permission_id =:permissionId")
    // List<String> findPermitById(@Param("permissionId") int permissionId);


}
