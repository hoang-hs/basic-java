package src.book.adapter.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import src.book.adapter.models.UserModel;
import src.book.core.entities.UserEntity;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserEntity userModelToEntity(UserModel userModel);

    UserModel userEntityToModel(UserEntity userEntity);

    List<UserEntity> usersModelToEntity(List<UserModel> usersModel);

}
