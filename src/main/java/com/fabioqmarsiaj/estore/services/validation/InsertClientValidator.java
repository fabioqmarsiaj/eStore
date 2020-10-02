package com.fabioqmarsiaj.estore.services.validation;

import com.fabioqmarsiaj.estore.domain.ClientType;
import com.fabioqmarsiaj.estore.dto.NewClientDTO;
import com.fabioqmarsiaj.estore.resources.exceptions.FieldMessage;
import com.fabioqmarsiaj.estore.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class InsertClientValidator implements ConstraintValidator<InsertClient, NewClientDTO> {
    @Override
    public void initialize(InsertClient ann) {
    }

    @Override
    public boolean isValid(NewClientDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getType().equals(ClientType.NATURALPERSON.getCod())
                && !BR.isValidCPF(objDto.getCpfOrCnpj())) {
            list.add(new FieldMessage("CpfOrCnpj", "Invalid CPF."));
        }

        if (objDto.getType().equals(ClientType.LEGALPERSON.getCod())
                && !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
            list.add(new FieldMessage("CpfOrCnpj", "Invalid CNPJ."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}