package br.com.thales.xyinc.service.validation;

import br.com.thales.xyinc.controller.exception.FieldMessage;
import br.com.thales.xyinc.dto.PoiDTO;
import br.com.thales.xyinc.model.Poi;
import br.com.thales.xyinc.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class PoiInsertValidator implements ConstraintValidator<PoiInsert, PoiDTO> {

    @Autowired
    private PoiRepository repo;

    @Override
    public void initialize(PoiInsert ann) {
    }

    @Override
    public boolean isValid(PoiDTO objDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        Poi aux = repo.findByNome(objDTO.getNome());
        if (aux != null) {
            list.add(new FieldMessage("nome", "Nome para POI já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }

}
