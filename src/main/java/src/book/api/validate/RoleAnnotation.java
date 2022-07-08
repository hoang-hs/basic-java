package src.book.api.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = RoleValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleAnnotation {
    String message() default "invalid role user";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
