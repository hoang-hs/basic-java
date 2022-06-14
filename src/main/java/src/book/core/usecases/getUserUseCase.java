package src.book.core.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import src.book.core.entities.response;
import src.book.core.entities.user;
import src.book.core.ports.iUserRepositoryPort;

import java.util.List;
import java.util.Optional;

@Component
public class getUserUseCase {

    private final iUserRepositoryPort userRepositoryPort;

    @Autowired
    public getUserUseCase(iUserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public response getAll() {
        return new response(userRepositoryPort.getAll());
    }

    public response getUserById(Long id) {
        Optional<user> user = userRepositoryPort.getUserById(id);
        if (user.isEmpty()) {
            return new response(HttpStatus.NOT_FOUND, "resource not found");
        }
        return new response(user.get());
    }
}
