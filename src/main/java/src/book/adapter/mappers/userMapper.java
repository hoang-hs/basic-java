package src.book.adapter.mappers;

import src.book.adapter.models.userModel;
import src.book.core.entities.user;

import java.util.ArrayList;
import java.util.List;

public class userMapper {
    public static List<user> convertUsersModelToEntity(List<userModel> userModels) {
        List<user> users = new ArrayList<>();
        for (userModel userModel : userModels) {
            users.add(new user(userModel.getId(), userModel.getUsername(), userModel.getPassword()));
        }
        return users;
    }

    public static user convertUserModelToEntity(userModel userModel) {
        return new user(userModel.getId(), userModel.getUsername(), userModel.getPassword());
    }
}
