package com.fabioqmarsiaj.estore.services.validation;

import com.fabioqmarsiaj.estore.domain.Client;
import com.fabioqmarsiaj.estore.domain.ClientType;
import com.fabioqmarsiaj.estore.dto.NewClientDTO;
import com.fabioqmarsiaj.estore.repositories.ClientRepository;
import com.fabioqmarsiaj.estore.resources.exceptions.FieldMessage;
import com.fabioqmarsiaj.estore.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class InsertClientValidator implements ConstraintValidator<InsertClient, NewClientDTO> {

    @Autowired
    private ClientRepository repo;

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

        Client aux = repo.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email already used."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}