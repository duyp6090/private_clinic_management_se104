/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.service.service_implementation;

import java.util.List;

import com.example.demo.domain.Permission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.IPermissionService;

/**
 *
 * @author iset1enloc
 */
public class PermissionServiceImpl implements IPermissionService {

    private final PermissionRepository permissionRepository;
    public PermissionServiceImpl(PermissionRepository permissionRepository){
        this.permissionRepository = permissionRepository;
    }
    @Override
    public List<Permission> getAllPermissions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPermissions'");
    }

    @Override
    public Permission getPermissionByRoleId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPermissionByRoleId'");
    }

    @Override
    public Permission save(Permission permission) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Permission updatePermission(int id, Permission permission) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePermission'");
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
