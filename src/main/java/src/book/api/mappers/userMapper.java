package src.book.api.mappers;

import src.book.api.resources.userResource;
import src.book.core.entities.userEntity;

import java.util.ArrayList;
import java.util.List;

public class userMapper {
    public static List<userResource> NewListUsersResource(List<userEntity> users) {
        List<userResource> usersResource = new ArrayList<>();
        for (userEntity user : users) {
            usersResource.add(new userResource(user.getId(), user.getUsername()));
        }
        return usersResource;
    }

    public static userResource NewUserResource(userEntity user) {
        return new userResource(user.getId(), user.getUsername());
    }

    public static userResource NewUserResourceWithPassword(userEntity user) {
        return new userResource(user.getId(), user.getUsername(), user.getPassword());
    }

}
