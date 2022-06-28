package src.book.api.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.book.api.resources.userResource;
import src.book.core.entities.userEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class userConvert {

    private final ModelMapper mapper;

    @Autowired
    public userConvert(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<userResource> convertListUsersEntityToResource(List<userEntity> users) {
        List<userResource> usersResource = new ArrayList<>();
        for (userEntity user : users) {
            usersResource.add(mapper.map(user, userResource.class));
        }
        return usersResource;
    }

    public userResource convertUserEntityToResource(userEntity user) {
        return mapper.map(user, userResource.class);
    }


}
