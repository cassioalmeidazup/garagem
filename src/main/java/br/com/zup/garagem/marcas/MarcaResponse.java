package br.com.zup.garagem.marcas;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class MarcaResponse {

    private String nome;

    private List<String> carros = new ArrayList<>();

    public MarcaResponse(Marca marca) {
        this.nome = marca.getNome();
        marca.getCarros().forEach(carro -> this.carros.add(carro.getModelo()));
    }

    public String getNome() {
        return nome;
    }

    public List<String> getCarros() {
        return carros;
    }
}
