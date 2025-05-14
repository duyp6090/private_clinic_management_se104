/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author iset1enloc
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreenPermission {
    @JsonProperty("role")
    private String role;

    @JsonProperty("permission_id")
    private int permission_id;

    @JsonProperty("permission")
    private String permissionName;

    @Override
    public String toString() {
        return "ScreenPermission [role_=" + role + ", permission_id=" + permission_id + ", permissionName="
                + permissionName + ", canCreate=" + canCreate + ", canUpdate=" + canUpdate + ", canRead=" + canRead
                + ", canDelete=" + canDelete + "]";
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    @JsonProperty("can_create")
    private Boolean canCreate;
    @JsonProperty("can_update")
    private Boolean canUpdate;

    @JsonProperty("can_read")
    private Boolean canRead;

    @JsonProperty("can_delete")
    private Boolean canDelete;
    public Boolean getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Boolean getCanCreate() {
        return canCreate;
    }

    public void setCanCreate(Boolean canCreate) {
        this.canCreate = canCreate;
    }

    public Boolean getCanRead() {
        return canRead;
    }

    public void setCanRead(Boolean canRead) {
        this.canRead = canRead;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    public static ScreenPermission fromRow(Object[] row) {
        ScreenPermission sp = new ScreenPermission();
        sp.setPermission_id(((Integer) row[0]));
        sp.setPermissionName((String) row[1]);
        sp.setCanCreate((Boolean) row[2]);
        sp.setCanRead((Boolean) row[3]);
        // Optional: if you want canUpdate
        sp.setCanUpdate((Boolean) row[4]); 
        sp.setCanDelete((Boolean) row[5]);
        return sp;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }
}
