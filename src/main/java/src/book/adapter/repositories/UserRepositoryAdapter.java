package src.book.adapter.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.book.adapter.mappers.UserMapper;
import src.book.adapter.models.UserModel;
import src.book.adapter.repositories.database.UserRepository;
import src.book.core.entities.UserEntity;
import src.book.core.ports.IUserRepositoryPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryAdapter implements IUserRepositoryPort {
    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAll() {
        List<UserModel> userModels = userRepository.findAll();
        return UserMapper.MAPPER.usersModelToEntity(userModels);
    }

    public Optional<UserEntity> getUserById(Long id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (userModel.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(UserMapper.MAPPER.userModelToEntity(userModel.get()));
    }

    public Optional<UserEntity> getUserByUsername(String username) {
        Optional<UserModel> userModel = userRepository.findByUsername(username);
        if (userModel.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(UserMapper.MAPPER.userModelToEntity(userModel.get()));
    }

    public UserEntity insertUser(UserEntity user) {
        UserModel UserModel = UserMapper.MAPPER.userEntityToModel(user);
        UserModel UserModelInsert = userRepository.save(UserModel);
        return UserMapper.MAPPER.userModelToEntity(UserModelInsert);
    }
}
