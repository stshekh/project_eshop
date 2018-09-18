package com.gmail.sshekh.dto;

import java.util.HashSet;
import java.util.Set;

public class RoleDTO {
    private Long idRole;
    private String roleName;
    private Set<PermissionDTO> permissions = new HashSet<>();

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionDTO> permissions) {
        this.permissions = permissions;
    }
}
