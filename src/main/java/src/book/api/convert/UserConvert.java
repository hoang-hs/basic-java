package src.book.api.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import src.book.api.resources.UserResource;
import src.book.core.entities.UserEntity;

import java.util.List;

@Mapper
public interface UserConvert {

    UserConvert Cloner = Mappers.getMapper(UserConvert.class);

    UserResource userEntityToResource(UserEntity user);

//    public List<UserResource> convertListUsersEntityToResource(List<UserEntity> users) {
//        List<UserResource> usersResource = new ArrayList<>();
//        for (UserEntity user : users) {
//            usersResource.add(mapper.map(user, UserResource.class));
//        }
//        return usersResource;
//    }
//
//    public UserResource convertUserEntityToResource(UserEntity user) {
//        return mapper.map(user, UserResource.class);
//    }
    List<UserResource> usersEntityToResource(List<UserEntity> usersEntity);
}
