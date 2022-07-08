package src.book.adapter.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.book.adapter.models.UserModel;
import src.book.core.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private final ModelMapper mapper;

    @Autowired
    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<UserEntity> mapperUsersModelToEntity(List<UserModel> UserModels) {
        List<UserEntity> users = new ArrayList<>();
        for (UserModel userModel : UserModels) {
            users.add(mapper.map(userModel, UserEntity.class));
        }
        return users;
    }

    public UserEntity mapperUserModelToEntity(UserModel UserModel) {
        return mapper.map(UserModel, UserEntity.class);
    }

    public UserModel mapperUserEntityToModel(UserEntity user) {
        return mapper.map(user, UserModel.class);
    }
}
