package src.book.core.ports;

import src.book.core.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserRepositoryPort {
    List<UserEntity> getAll();

    Optional<UserEntity> getUserById(Long id);

    Optional<UserEntity> getUserByUsername(String username);

    UserEntity insertUser(UserEntity user);
}
