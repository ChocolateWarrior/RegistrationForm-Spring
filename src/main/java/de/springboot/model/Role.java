package de.springboot.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    GUEST,
    MASTER;

    @Override
    public String getAuthority() {
        return name();
    }

}
