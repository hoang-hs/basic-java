package src.book.adapter.mappers;

import src.book.adapter.models.userModel;
import src.book.core.entities.userEntity;

import java.util.ArrayList;
import java.util.List;

public class userMapper {
    public static List<userEntity> convertUsersModelToEntity(List<userModel> userModels) {
        List<userEntity> users = new ArrayList<>();
        for (userModel userModel : userModels) {
            users.add(new userEntity(userModel.getId(), userModel.getUsername(), userModel.getPassword()));
        }
        return users;
    }

    public static userEntity convertUserModelToEntity(userModel userModel) {
        return new userEntity(userModel.getId(), userModel.getUsername(), userModel.getPassword());
    }

    public static userModel convertUserEntityToModel(userEntity user) {
        return new userModel(user.getUsername(), user.getPassword());
    }
}
