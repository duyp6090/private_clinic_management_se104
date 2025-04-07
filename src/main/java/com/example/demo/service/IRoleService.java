/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Role;

/**
 *
 * @author iset1enloc
 */
public interface IRoleService {
    List<Role> getAllRoles();
    Role getRoleById(int id);
    Role save(Role role);
    Role updateRoleById(int id, Role role);
    void deleteRoleById(int id);
}
