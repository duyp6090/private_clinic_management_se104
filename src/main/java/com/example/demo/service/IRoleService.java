/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Role;
import com.example.demo.dto.response.ScreenPermission;

/**
 *
 * @author iset1enloc
 */
public interface IRoleService {
    List<Role> getAllRoles();
    Role getRoleById(int id);
    int getRoleIDByRoleName(String roleName);
    Role save(Role role);
    Role updateRoleById(int id, Role role);
    void deleteRoleById(int id);

    Role assignPermissionToRole(int roleId, int permissionId);
    Role removePermissionFromRole(int roleId, int permissionId);
    Boolean updateRolePermissionAbilability(ScreenPermission permission);
}
