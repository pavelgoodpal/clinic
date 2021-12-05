package com.cshop.cosmeticshop.domain.intity.constants;

public enum Permission {
    READ("PERMISSION_READ"),
    WRITE("PERMISSION_WRITE");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
