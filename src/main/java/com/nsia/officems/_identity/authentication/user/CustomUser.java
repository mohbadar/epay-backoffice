package com.nsia.officems._identity.authentication.user;

import java.util.Collection;
import java.util.List;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class CustomUser extends User {

    private static final long serialVersionUID = -3531439484732724601L;

    private String currentLang;
    private String currentEnv;
    private String entity;
    private Long entityId;
    private String department;
    private Long departmentId;
    private List<Long> accessableDepartments;
    
    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
            String currentEnv, String currentLang, Long entityId, String entity, Long departmentId, String department, List<Long> accessableDepartments) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.currentEnv = currentEnv;
        this.currentLang = currentLang;
        this.entity = entity;
        this.entityId = entityId;
        this.department = department;
        this.departmentId = departmentId;
        this.accessableDepartments = accessableDepartments;
    }

}
