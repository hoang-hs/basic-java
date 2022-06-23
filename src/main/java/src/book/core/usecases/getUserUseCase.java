package src.book.core.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.book.core.entities.user;
import src.book.core.ports.iUserRepositoryPort;
import src.book.exception.resourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Component
public class getUserUseCase {

    private static final Logger logger = LoggerFactory.getLogger(getUserUseCase.class);
    private final iUserRepositoryPort userRepositoryPort;

    @Autowired
    public getUserUseCase(iUserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public List<user> getAll() {
        return userRepositoryPort.getAll();
    }

    public user getUserById(Long id) {
        Optional<user> user = userRepositoryPort.getUserById(id);
        if (user.isEmpty()) {
            logger.warn("user not found, id: {}", id);
            throw resourceNotFoundException.Default();
        }
        return user.get();
    }
}
