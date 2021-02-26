package br.com.zup.garagem.marcas.validation;

import br.com.zup.garagem.marcas.Marca;
import br.com.zup.garagem.marcas.MarcaRepository;
import br.com.zup.garagem.marcas.MarcaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ValidaSeMarcaEUnica implements Validator {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return MarcaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if(errors.hasErrors()) return ;

        MarcaRequest request = (MarcaRequest) o;

        Optional<Marca> marcaExistente = marcaRepository.findByNome(request.getNome());

        if(marcaExistente.isPresent()){
            errors.rejectValue("nome", "Nome da marca já existe");
        }
    }
}
