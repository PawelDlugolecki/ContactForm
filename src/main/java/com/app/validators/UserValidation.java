package com.app.validators;

import com.app.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidation implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

            User user = (User) o;

            if (user.getName() == null) {
                errors.rejectValue("name", "The name field can not be empty");
            }

            if (user.getSurname() == null) {
                errors.rejectValue("surname", "The surname field can not be empty");
            }

            if (user.getPhone() == null || user.getEmail() == null) {
                errors.rejectValue("phone|number", "One of the fields: telephone or e mail - must be filled out");
            }

    }
}
