package src.book.api.validate;

import org.yaml.snakeyaml.util.EnumUtils;
import src.book.core.enums.roles;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<roleAnnotation, String> {

    @Override
    public void initialize(roleAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return roles.isMember(value);
    }
}
