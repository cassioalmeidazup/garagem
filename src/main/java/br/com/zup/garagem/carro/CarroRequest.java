package br.com.zup.garagem.carro;

import javax.validation.constraints.NotBlank;

public class CarroRequest {

    @NotBlank
    private String modelo;

    @NotBlank
    private String marca;

    @NotBlank
    private String tipo;

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getTipo() {
        return tipo;
    }
}
