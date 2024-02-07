package com.example.LabTech.entite.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.example.LabTech.entite.enums.Permission
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static com.example.LabTech.entite.enums.Permission.WRITE;
import static com.example.LabTech.entite.enums.Permission.READ;
import static com.example.LabTech.entite.enums.Permission.DELETE;

@RequiredArgsConstructor
public enum Role {
    TECHNICIEN("ROLE_TECHNICIEN", READ, WRITE),
    RESPONSABLE("ROLE_RESPONSABLE", READ, WRITE,DELETE);

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
