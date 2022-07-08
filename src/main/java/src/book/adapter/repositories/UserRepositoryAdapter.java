package src.book.adapter.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.book.adapter.mappers.UserMapper;
import src.book.adapter.models.UserModel;
import src.book.adapter.repositories.database.UserRepository;
import src.book.core.entities.UserEntity;
import src.book.core.ports.IUserRepositoryPort;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryAdapter implements IUserRepositoryPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserEntity> getAll() {
        return userMapper.mapperUsersModelToEntity(userRepository.findAll());
    }

    public Optional<UserEntity> getUserById(Long id) {
        Optional<UserModel> UserModel = userRepository.findById(id);
        if (UserModel.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userMapper.mapperUserModelToEntity(UserModel.get()));
    }

    public Optional<UserEntity> getUserByUsername(String username) {
        Optional<UserModel> userModel = userRepository.findByUsername(username);
        if (userModel.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userMapper.mapperUserModelToEntity(userModel.get()));
    }

    public UserEntity insertUser(UserEntity user) {
        UserModel UserModel = userMapper.mapperUserEntityToModel(user);
        UserModel UserModelInsert = userRepository.save(UserModel);
        return userMapper.mapperUserModelToEntity(UserModelInsert);
    }
}
