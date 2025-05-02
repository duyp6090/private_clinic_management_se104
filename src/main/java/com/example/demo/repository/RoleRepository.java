/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Role;

/**
 *
 * @author iset1enloc
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("SELECT r.role_name " +
    "FROM Role r " +
    "JOIN Role_Permission rp " +
    "JOIN rp.permission p " +
    "WHERE r.role_id = :roleId")
    List<String> getPermissionsByRoleId(@Param("roleId") int roleId);
    
    @Query("SELECT r.role_id FROM Role r WHERE r.role_name = :roleName")
    int getRoleIdByRoleName(@Param("roleName") String roleName);
}
