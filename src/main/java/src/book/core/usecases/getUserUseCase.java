package src.book.core.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import src.book.core.entities.userEntity;
import src.book.core.ports.iUserRepositoryPort;
import src.book.exception.resourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class getUserUseCase {

    private static final Logger logger = LoggerFactory.getLogger(getUserUseCase.class);
    private final iUserRepositoryPort userRepositoryPort;

    @Autowired
    public getUserUseCase(iUserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public List<userEntity> getAll() {
        return userRepositoryPort.getAll();
    }

    public userEntity getUserById(Long id) {
        Optional<userEntity> user = userRepositoryPort.getUserById(id);
        if (user.isEmpty()) {
            logger.warn("user not found, id: [{}]", id);
            throw resourceNotFoundException.Default();
        }
        return user.get();
    }
}
