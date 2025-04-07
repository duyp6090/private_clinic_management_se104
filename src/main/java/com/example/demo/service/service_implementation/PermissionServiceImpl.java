/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.service.service_implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Permission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.IPermissionService;

/**
 *
 * @author iset1enloc
 */

@Service
public class PermissionServiceImpl implements IPermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public List<String> getPermissionsByRoleId(int roleId) {
        return permissionRepository.getPermissionsByRoleId(roleId);
    }
    

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(int id, Permission updatedPermission) {
        Optional<Permission> optionalPermission = permissionRepository.findById(id);
        if (optionalPermission.isPresent()) {
            Permission existingPermission = optionalPermission.get();
            existingPermission.setPermission(updatedPermission.getPermission());
            existingPermission.setDescription(updatedPermission.getDescription());
            return permissionRepository.save(existingPermission);
        } else {
            throw new RuntimeException("Permission not found with id: " + id);
        }
    }

    @Override
    public void delete(int id) {
        permissionRepository.deleteById(id);
    }
}
