package br.com.zup.garagem.marcas;

import br.com.zup.garagem.marcas.validation.ValidaSeMarcaEUnica;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ValidaSeMarcaEUnica validaSeMarcaEUnica;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(validaSeMarcaEUnica);
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid MarcaRequest marcaRequest,
                                                  UriComponentsBuilder uriComponentsBuilder){


        Marca marca = new Marca(marcaRequest.getNome());

        marcaRepository.save(marca);

        return ResponseEntity.created(uriComponentsBuilder.path("/marcas/{id}")
                .buildAndExpand(marca.getId().toString()).toUri())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponse> busca(@PathVariable("id") Long id){

        Optional<Marca> marca = marcaRepository.findById(id);

        if(marca.isPresent()){
            return ResponseEntity.ok(new MarcaResponse(marca.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<MarcaResponse>> lista(){

        List<Marca> all = marcaRepository.findAll();
        List<MarcaResponse> marcaResponseList = all.stream().map(MarcaResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(marcaResponseList);
    }


}
