package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Role;
import com.example.demo.dto.RestResponse;
import com.example.demo.dto.role.newRoleReq;
import com.example.demo.service.IRoleService;

@RestController
@RequestMapping("/api/admin/role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<Role>>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(RestResponse.success(200, roles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<Role>> getRoleById(@PathVariable int id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(RestResponse.success(200, role));
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<Role>> createRole(@RequestBody newRoleReq role) {
        Role newRole = new Role();
        System.out.println(role.rolename);
        newRole.setRole_name(role.rolename);
        newRole.setDescription(role.description);
        System.out.println(role);
        Role savedRole = roleService.save(newRole);
        return ResponseEntity.ok(RestResponse.success(201, savedRole));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<Role>> updateRole(@PathVariable int id, @RequestBody Role role) {
        Role updatedRole = roleService.updateRoleById(id, role);
        return ResponseEntity.ok(RestResponse.success(200, updatedRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<String>> deleteRole(@PathVariable int id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.ok(RestResponse.success(200, "Role deleted successfully."));
    }
    @PutMapping("/{roleId}/assign-permissions")
    public ResponseEntity<RestResponse<Role>> assignPermissionsToRole(
            @PathVariable int roleId,
            @RequestBody int permission_id) {
        Role updatedRole = roleService.assignPermissionToRole(roleId, permission_id);
        return ResponseEntity.ok(RestResponse.success(HttpStatus.OK.value(), updatedRole));
    }

    @PutMapping("/{roleId}/remove-permission/{permissionId}")
    public ResponseEntity<RestResponse<Role>> removePermissionFromRole(
            @PathVariable int roleId,
            @PathVariable int permissionId) {
        Role updatedRole = roleService.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.ok(RestResponse.success(HttpStatus.OK.value(), updatedRole));
    }

}
