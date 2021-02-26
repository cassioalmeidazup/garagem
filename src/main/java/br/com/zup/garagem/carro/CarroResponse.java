package br.com.zup.garagem.carro;

public class CarroResponse {

    String modelo;

    String marca;



    public CarroResponse(Carro carro) {
        modelo = carro.getModelo();
        marca = carro.getMarca().getNome();
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

}
