package src.book.api.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.book.api.resources.TokenResource;
import src.book.core.entities.TokenEntity;

@Component
public class TokenConvert {
    private final ModelMapper mapper;

    @Autowired
    public TokenConvert(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public TokenResource convertTokenEntityToResource(TokenEntity token) {
        return mapper.map(token, TokenResource.class);
    }
}
