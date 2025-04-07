/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.service.service_implementation;

import java.util.List;

import com.example.demo.domain.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.IRoleService;

/**
 *
 * @author iset1enloc
 */
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleservicRepository){
        this.roleRepository = roleservicRepository;
    }
    @Override
    public List<Role> getAllRoles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRoles'");
    }

    @Override
    public Role getRoleById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRoleById'");
    }

    @Override
    public Role save(Role role) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Role updateRoleById(int id, Role role) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRoleById'");
    }

    @Override
    public void deleteRoleById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRoleById'");
    }

}
