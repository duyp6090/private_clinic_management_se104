/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Permission;

/**
 *
 * @author iset1enloc
 */
public interface IPermissionService {
    List<Permission> getAllPermissions();
    List<String> getPermissionsByRoleId(int id);
    Permission save(Permission permission);
    Permission updatePermission(int id, Permission permission);
    void delete(int id);
}
