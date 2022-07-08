package src.book.core.enums;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.book.exception.SystemErrorException;

public enum Roles {

    ADMIN("ADMIN"),
    CLIENT("CLIENT");
    private final String role;

    private static final Logger logger = LoggerFactory.getLogger(Roles.class);

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public static @NotNull Roles fromString(String role) {
        for (Roles r : Roles.values()) {
            if (r.getRole().equals((role))) {
                return r;
            }
        }
        logger.warn("role invalid, role: {}", role);
        throw SystemErrorException.Default();
    }

    public static boolean isMember(String role) {
        Roles[] allRole = Roles.values();
        for (Roles r : allRole) {
            if (r.role.equals(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return role;
    }

}

