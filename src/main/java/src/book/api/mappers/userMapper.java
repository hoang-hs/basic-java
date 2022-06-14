package src.book.api.mappers;

import src.book.api.resources.userResource;
import src.book.core.entities.user;

import java.util.ArrayList;
import java.util.List;

public class userMapper {
    public static List<userResource> NewListUsersResource(List<user> users) {
        List<userResource> usersResource = new ArrayList<>();
        for (user user : users) {
            usersResource.add(new userResource(user.getId(), user.getUsername()));
        }
        return usersResource;
    }

    public static userResource NewUserResource(user user) {
        return new userResource(user.getId(), user.getPassword());
    }
}
