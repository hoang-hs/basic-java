package src.book.present.validate;

import src.book.core.enums.Role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<RoleAnnotation, String> {

    @Override
    public void initialize(RoleAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Role.isMember(value);
//                boolean isMember = Roles.isMember(value);
//        if (!isMember) {
//            throw badRequestException.WithMessage("role invalid");
//        }
//        return true;
    }
}
