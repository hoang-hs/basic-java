package src.book.core.ports;

import src.book.core.entities.user;

import java.util.List;
import java.util.Optional;

public interface iUserRepositoryPort {
    List<user> getAll();

    Optional<user> getUserById(Long id);
}
