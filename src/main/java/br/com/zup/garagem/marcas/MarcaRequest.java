package br.com.zup.garagem.marcas;

import javax.validation.constraints.NotBlank;

public class MarcaRequest {

    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }
}
