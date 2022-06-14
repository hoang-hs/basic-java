package src.book.core.usecases;

import src.book.core.entities.user;
import src.book.core.ports.iUserRepositoryPort;

import java.util.List;

public class getBookUseCase {
    private final iUserRepositoryPort userRepositoryPort;

    public getBookUseCase(iUserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public List<user> getAllUser() {
        return userRepositoryPort.getAll();
    }
}
