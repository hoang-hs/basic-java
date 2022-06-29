package src.book.api.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.book.api.resources.tokenResource;
import src.book.core.entities.tokenEntity;

@Component
public class tokenConvert {
    private final ModelMapper mapper;

    @Autowired
    public tokenConvert(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public tokenResource convertTokenEntityToResource(tokenEntity token) {
        return mapper.map(token, tokenResource.class);
    }
}
