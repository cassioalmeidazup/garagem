package br.com.zup.garagem.carro;

import br.com.zup.garagem.marcas.Marca;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"modelo","marca"})})
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "modelo")
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "marca")
    private Marca marca;

    @Enumerated(EnumType.STRING)
    private TipoCarro tipo;

    public Carro() {
    }

    public Carro(String modelo, Marca marca, TipoCarro tipo) {
        this.modelo = modelo;
        this.marca = marca;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public Marca getMarca() {
        return marca;
    }
}
