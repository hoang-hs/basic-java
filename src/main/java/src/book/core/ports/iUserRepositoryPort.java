package src.book.core.ports;

import src.book.core.entities.userEntity;

import java.util.List;
import java.util.Optional;

public interface iUserRepositoryPort {
    List<userEntity> getAll();

    Optional<userEntity> getUserById(Long id);

    Optional<userEntity> getUserByUsername(String username);

    userEntity insertUser(userEntity user);
}
