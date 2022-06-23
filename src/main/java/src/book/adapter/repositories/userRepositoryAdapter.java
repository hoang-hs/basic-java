package src.book.adapter.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.book.adapter.mappers.userMapper;
import src.book.adapter.models.userModel;
import src.book.adapter.repositories.database.userRepository;
import src.book.core.entities.userEntity;
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

    public List<userEntity> getAll() {
        return userMapper.convertUsersModelToEntity(userRepository.findAll());
    }

    public Optional<userEntity> getUserById(Long id) {
        Optional<userModel> userModel = userRepository.findById(id);
        if (userModel.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userMapper.convertUserModelToEntity(userModel.get()));
    }

    public Optional<userEntity> getUserByUsername(String username) {
        Optional<userModel> userModel = userRepository.findByUsername(username);
        if (userModel.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userMapper.convertUserModelToEntity(userModel.get()));
    }

    public userEntity insertUser(userEntity user) {
        userModel userModel = userMapper.convertUserEntityToModel(user);
        userModel userModelInsert = userRepository.save(userModel);
        return userMapper.convertUserModelToEntity(userModelInsert);
    }
}
