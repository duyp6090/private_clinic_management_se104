/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Role_Permission;
import com.example.demo.domain.Role_PermissionId;

import jakarta.transaction.Transactional;

/**
 *
 * @author iset1enloc
 */
@Repository
public interface Role_PermissionRepository extends JpaRepository<Role_Permission, Role_PermissionId> {

    @Query("SELECT COUNT(rp) > 0 FROM Role_Permission rp WHERE rp.role.role_id = :roleId AND rp.permission.permission_id = :permissionId")
    boolean existsByRole_RoleIdAndPermission_PermissionId(@Param("roleId") int roleId, @Param("permissionId") int permissionId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Role_Permission rp WHERE rp.role.role_id = :roleId AND rp.permission.permission_id = :permissionId")
    void deleteByRole_RoleIdAndPermission_PermissionId(@Param("roleId") int roleId, @Param("permissionId") int permissionId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO role_permission(role_id, permission_id) VALUES(:roleId, :permissionId)", nativeQuery = true)
    void addByRole_RoleIdAndPermission_PermissionId(@Param("roleId") int roleId, @Param("permissionId") int permissionId);

    @Query("Delete from Role_Permission rp where rp.role.role_id = :roleId")
    @Modifying
    @Transactional
    void deleteByRole_RoleId(@Param("roleId") int roleId);
}
