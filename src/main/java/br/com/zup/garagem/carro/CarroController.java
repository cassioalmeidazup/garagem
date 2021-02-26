package br.com.zup.garagem.carro;

import br.com.zup.garagem.marcas.Marca;
import br.com.zup.garagem.marcas.MarcaRepository;
import br.com.zup.garagem.marcas.MarcaRequest;
import br.com.zup.garagem.marcas.MarcaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private CarroRepository carroRepository;

    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid CarroRequest carroRequest,
                                   UriComponentsBuilder uriComponentsBuilder){

        Optional<Marca> marca = marcaRepository.findByNome(carroRequest.getMarca());

        if(marca.isPresent()){
            Carro carro = new Carro(carroRequest.getModelo(),marca.get(),
                    TipoCarro.valueOf(carroRequest.getTipo()));
            carroRepository.save(carro);

            return ResponseEntity.created(uriComponentsBuilder.path("/carros/{id}")
                    .buildAndExpand(carro.getId().toString()).toUri())
                    .build();
        }
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroResponse> busca(@PathVariable("id") Long id){

        Optional<Carro> carro = carroRepository.findById(id);

        if(carro.isPresent()){
            return ResponseEntity.ok(new CarroResponse(carro.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CarroResponse>> lista(){

        List<Carro> all = carroRepository.findAll();
        List<CarroResponse> carroResponseList = all.stream().map(CarroResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(carroResponseList);
    }
}
