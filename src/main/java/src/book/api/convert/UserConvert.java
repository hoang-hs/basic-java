package src.book.api.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.book.api.resources.UserResource;
import src.book.core.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConvert {

    private final ModelMapper mapper;

    @Autowired
    public UserConvert(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<UserResource> convertListUsersEntityToResource(List<UserEntity> users) {
        List<UserResource> usersResource = new ArrayList<>();
        for (UserEntity user : users) {
            usersResource.add(mapper.map(user, UserResource.class));
        }
        return usersResource;
    }

    public UserResource convertUserEntityToResource(UserEntity user) {
        return mapper.map(user, UserResource.class);
    }


}
