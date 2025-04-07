package com.example.demo.service.service_implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Permission;
import com.example.demo.domain.Role;
import com.example.demo.domain.Role_Permission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.Role_PermissionRepository;
import com.example.demo.service.IRoleService;
/**
 *
 * @author iset1enloc
 */
@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private Role_PermissionRepository rolePermissionRepository;
    public RoleServiceImpl(RoleRepository roleRepository,PermissionRepository permissionRepository,Role_PermissionRepository rolePermissionRepository){
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(int id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        return optionalRole.orElse(null); // Or throw a custom NotFoundException
    }

    @Override
    public Role save(Role role) {
        System.out.println(role);
        return roleRepository.save(role);
    }

    @Override
    public Role updateRoleById(int id, Role roleDetails) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.setRole_name(roleDetails.getRole_name());
            role.setDescription(roleDetails.getDescription());
            return roleRepository.save(role);
        }
        return null; // Or throw NotFoundException
    }

    @Override
    public void deleteRoleById(int id) {
        roleRepository.deleteById(id);
    }


    @Override
    public Role assignPermissionToRole(int roleId, int permissionId) {
        // Fetch the role
        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new RuntimeException("Role with ID " + roleId + " not found"));
    
        // Fetch the permission
        Permission permission = permissionRepository.findById(permissionId)
            .orElseThrow(() -> new RuntimeException("Permission with ID " + permissionId + " not found"));
    
        // Check if already assigned
        boolean alreadyAssigned = rolePermissionRepository.existsByRole_RoleIdAndPermission_PermissionId(roleId, permissionId);
        if (alreadyAssigned) {
            throw new RuntimeException("Permission already assigned to the role");
        }
    
        // Save the relationship
        Role_Permission rolePermission = new Role_Permission(role, permission);
        rolePermissionRepository.save(rolePermission);
    
        return role;
    }
    

    @Override
    public Role removePermissionFromRole(int roleId, int permissionId) {
        boolean exists = rolePermissionRepository.existsByRole_RoleIdAndPermission_PermissionId(roleId, permissionId);
        if (!exists) throw new RuntimeException("Permission not assigned to role");

        rolePermissionRepository.deleteByRole_RoleIdAndPermission_PermissionId(roleId, permissionId);

        return roleRepository.findById(roleId)
            .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    


}
