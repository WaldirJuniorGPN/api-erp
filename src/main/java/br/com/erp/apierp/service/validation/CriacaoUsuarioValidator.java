package br.com.erp.apierp.service.validation;

import br.com.erp.apierp.dto.request.RequestLojaAutomatizado;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CriacaoUsuarioValidator implements ConstraintValidator<CriacaoUsuarioValid, RequestLojaAutomatizado> {

    @Override
    public void initialize(CriacaoUsuarioValid anotation){}

    @Override
    public boolean isValid(RequestLojaAutomatizado requestLojaAutomatizado, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
