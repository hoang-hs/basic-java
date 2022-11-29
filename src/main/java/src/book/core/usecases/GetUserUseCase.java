package src.book.core.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.book.core.entities.UserEntity;
import src.book.core.ports.IUserRepositoryPort;
import src.book.core.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class GetUserUseCase {

    private static final Logger logger = LoggerFactory.getLogger(GetUserUseCase.class);
    private final IUserRepositoryPort userRepositoryPort;

    @Autowired
    public GetUserUseCase(IUserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public List<UserEntity> getAll() {
        return userRepositoryPort.getAll();
    }

    public UserEntity getUserById(Long id) {
        Optional<UserEntity> user = userRepositoryPort.getUserById(id);
        if (user.isEmpty()) {
            logger.warn("user not found, id: [{}]", id);
            throw ResourceNotFoundException.Default();
        }
        return user.get();
    }

    public UserEntity getUserByUserName(String username) {
        Optional<UserEntity> user = userRepositoryPort.getUserByUsername(username);
        if (user.isEmpty()) {
            logger.warn("user not found, username: [{}]", username);
            throw ResourceNotFoundException.Default();
        }
        return user.get();
    }
}
