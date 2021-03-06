package ru.kino.demo.model.validations;




import ru.kino.demo.model.UserRegisterBindingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordMatchingValidator implements ConstraintValidator<IsPasswordMatching,Object> {
    @Override
    public void initialize(IsPasswordMatching isPasswordMatching) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof UserRegisterBindingModel){
            UserRegisterBindingModel userModel = (UserRegisterBindingModel) object;
            return userModel.getPassword().equals(userModel.getConfirmPassword());
        }
        return false;
    }
}
