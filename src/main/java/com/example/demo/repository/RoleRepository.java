/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = """
        SELECT r.role_id
        FROM users u 
        JOIN tbl_user_role ur
        JOIN role r
        WHERE u.user_id = ur.user_id AND ur.role_id = r.role_id AND u.username = user_name
        """, nativeQuery = true)
    int getRoleIdByUserName(@Param("user_name") String username);
    @Modifying
    @Query(value = """
        UPDATE tbl_role_permission 
        SET can_create = :canCreate,
            can_read = :canRead,
            can_update = :canUpdate,
            can_delete = :canDelete
        WHERE role_id = :role_id AND permission_id = :permission_id
        """, nativeQuery = true)
    int updateRolePermissionAbility(@Param("role_id") int role_id,
                                    @Param("permission_id") int permission_id,
                                    @Param("canCreate") Boolean canCreate,
                                    @Param("canRead") Boolean canRead,
                                    @Param("canUpdate") Boolean canUpdate,
                                    @Param("canDelete") Boolean canDelete);

}
