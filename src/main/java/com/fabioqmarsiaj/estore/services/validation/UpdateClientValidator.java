package com.fabioqmarsiaj.estore.services.validation;

import com.fabioqmarsiaj.estore.domain.Client;
import com.fabioqmarsiaj.estore.dto.ClientDTO;
import com.fabioqmarsiaj.estore.repositories.ClientRepository;
import com.fabioqmarsiaj.estore.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateClientValidator implements ConstraintValidator<UpdateClient, ClientDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository repo;

    @Override
    public void initialize(UpdateClient ann) {
    }

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.valueOf(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Client aux = repo.findByEmail(objDto.getEmail());
        if (aux != null &&
                !aux.getId().equals(uriId)) {

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