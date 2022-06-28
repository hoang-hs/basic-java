package src.book.adapter.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.book.adapter.models.userModel;
import src.book.core.entities.userEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class userMapper {

    private final ModelMapper mapper;

    @Autowired
    public userMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<userEntity> mapperUsersModelToEntity(List<userModel> userModels) {
        List<userEntity> users = new ArrayList<>();
        for (userModel userModel : userModels) {
            users.add(mapper.map(userModel, userEntity.class));
        }
        return users;
    }

    public userEntity mapperUserModelToEntity(userModel userModel) {
        return mapper.map(userModel, userEntity.class);
    }

    public userModel mapperUserEntityToModel(userEntity user) {
        return mapper.map(user, userModel.class);
    }
}
