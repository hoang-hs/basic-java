package src.book.core.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import src.book.core.usecases.getUserUseCase;
import src.book.exception.systemErrorException;

public enum roles {

    ADMIN("ADMIN"),
    CLIENT("CLIENT");
    private final String role;

    private static final Logger logger = LoggerFactory.getLogger(getUserUseCase.class);

    roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public static roles fromString(String role) {
        for (roles r : roles.values()) {
            if (r.getRole().equals((role))) {
                return r;
            }
        }
        logger.warn("role invalid, role: {}", role);
        throw systemErrorException.Default();
    }

    public static boolean isMember(String role) {
        roles[] allRole = roles.values();
        for (roles r : allRole) {
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

