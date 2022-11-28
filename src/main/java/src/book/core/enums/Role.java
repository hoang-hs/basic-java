package src.book.core.enums;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import src.book.core.exception.SystemErrorException;

public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),
    CLIENT("CLIENT");
    private final String role;

    private static final Logger logger = LoggerFactory.getLogger(Role.class);

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public static @NotNull Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.getRole().equals((role))) {
                return r;
            }
        }
        logger.warn("role invalid, role: {}", role);
        throw SystemErrorException.Default();
    }

    public static boolean isMember(String role) {
        Role[] allRole = Role.values();
        for (Role r : allRole) {
            if (r.role.equals(role)) {
                return true;
            }
        }
        return false;
    }

    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }

}

