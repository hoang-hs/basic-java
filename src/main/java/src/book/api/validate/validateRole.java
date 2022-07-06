package src.book.api.validate;

import javax.validation.Payload;

public @interface validateRole {

    String[] acceptedValues();

    String message() default "{uk.dds.ideskos.validator.ValidateString.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
