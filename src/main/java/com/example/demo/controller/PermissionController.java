package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.domain.Permission;
import com.example.demo.dto.permission.newPermission;
import com.example.demo.dto.response.RestResponse;
import com.example.demo.service.IPermissionService;

@RestController
@RequestMapping("/api/admin/permission")
@PreAuthorize("hasRole('ADMIN')")
public class PermissionController {

    private final IPermissionService permissionService;

    public PermissionController(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<Permission>>> getAllPermissions() {
        List<Permission> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(RestResponse.success(200, permissions));
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<RestResponse<List<String>>> getPermissionsByRoleId(@PathVariable int roleId) {
        List<String> permissionNames = permissionService.getPermissionsByRoleId(roleId);
        return ResponseEntity.ok(RestResponse.success(200, permissionNames));
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<Permission>> createPermission(@RequestBody newPermission permission) {
        Permission newPermit = new Permission();
        newPermit.setDescription(permission.description);
        newPermit.setPermission(permission.permission);
        Permission savedPermission = permissionService.save(newPermit);
        return ResponseEntity.ok(RestResponse.success(201, savedPermission));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<Permission>> updatePermission(@PathVariable int id,
            @RequestBody Permission permission) {
        Permission updated = permissionService.updatePermission(id, permission);
        return ResponseEntity.ok(RestResponse.success(200, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<String>> deletePermission(@PathVariable int id) {
        permissionService.delete(id);
        return ResponseEntity.ok(RestResponse.success(200, "Permission deleted successfully."));
    }
}
