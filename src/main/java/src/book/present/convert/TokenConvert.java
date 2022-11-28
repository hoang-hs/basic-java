package src.book.present.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import src.book.present.resources.TokenResource;
import src.book.core.entities.TokenEntity;

@Mapper
public interface TokenConvert {;

    TokenConvert cloner = Mappers.getMapper(TokenConvert.class);

    TokenResource tokenEntityToResource(TokenEntity tokenEntity);
}
