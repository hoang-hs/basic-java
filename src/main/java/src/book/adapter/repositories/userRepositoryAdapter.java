package src.book.adapter.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.book.adapter.mappers.userMapper;
import src.book.adapter.models.userModel;
import src.book.adapter.repositories.database.userRepository;
import src.book.core.entities.user;
import src.book.core.ports.iUserRepositoryPort;

import java.util.List;
import java.util.Optional;

@Component
public class userRepositoryAdapter implements iUserRepositoryPort {
    private final userRepository userRepository;

    @Autowired
    public userRepositoryAdapter(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<user> getAll() {
        return userMapper.convertUsersModelToEntity(userRepository.findAll());
    }

    public Optional<user> getUserById(Long id) {
        Optional<userModel> userModel = userRepository.findById(id);
        if (userModel.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userMapper.convertUserModelToEntity(userModel.get()));
    }
}
