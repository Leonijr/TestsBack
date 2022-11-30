package br.com.apiservicos.apiservicos.services.validation;

import br.com.apiservicos.apiservicos.controller.dto.ResetPasswordDTO;
import br.com.apiservicos.apiservicos.exceptions.FieldMessage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ChangePasswordSaveValidator implements ConstraintValidator<ChagePasswordSave, ResetPasswordDTO> {

    @Override
    public boolean isValid(ResetPasswordDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> erros = new ArrayList<>();

        //Make custom validations here and add that in que erros list
        if(!objDto.getPassword().equals(objDto.getPasswordConfirm())) {
            erros.add(new FieldMessage("password","passord and password confirm not match!"));
        }

        for (FieldMessage e : erros) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return erros.isEmpty();
    }
}