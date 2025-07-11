package com.example.demo.service.service_implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Permission;
import com.example.demo.domain.Role;
import com.example.demo.domain.Role_Permission;
import com.example.demo.dto.response.ScreenPermission;
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
    private final Role_PermissionRepository rolePermissionRepository;
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
    @Transactional
    @Override
    public Role save(Role role) {
        System.out.println(role);
        Role savedRole = roleRepository.save(role);
        System.out.println("Saved Role ID: " + savedRole.getRole_id());

        
        List<Permission> permissions = permissionRepository.findAll();
        System.out.print("Enter line 50");
        for(Permission permission:permissions){
            System.out.println(savedRole.getRole_id());
            System.out.println("Permission id: "+permission.getPermission_id());
                    // Save the relationship
            Role_Permission rolePermission = new Role_Permission(role, permission);
            rolePermissionRepository.save(rolePermission);
            roleRepository.updateRolePermissionAbility(savedRole.getRole_id(), permission.getPermission_id(),
                                                        false,false,false,false);
        }
        return savedRole;
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
    @Transactional
    public void deleteRoleById(int id) {
        rolePermissionRepository.deleteByRole_RoleId(id);
        roleRepository.deleteById(id);
    }

    @Transactional
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

    @Override
    public int getRoleIDByRoleName(String roleName) {
        return roleRepository.getRoleIdByRoleName(roleName);
    }
    @Transactional
    @Override
    public Boolean updateRolePermissionAbilability(ScreenPermission permission) {
  
        try {
            System.out.println(permission);
            System.out.println("ENTER LINE 110");
            int id =roleRepository.getRoleIdByRoleName(permission.getRole());
            return roleRepository.updateRolePermissionAbility(
                        id, 
                        permission.getPermission_id(),
                        permission.getCanCreate(),
                        permission.getCanRead(),
                        permission.getCanUpdate(),
                        permission.getCanDelete()
                   ) > 0;
    
        } catch (Exception e) {
            System.out.printf("Error updating role permission ability for role_id={}, permission_id={}: {}", 
                         permission != null ? permission.getRole() : "null",
                         permission != null ? permission.getPermission_id() : "null",
                         e.getMessage(), e);
            System.out.println(e);
            return false;
        }
    }


}
