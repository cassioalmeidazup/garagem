package br.com.zup.garagem.marcas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca,Long> {

    Optional<Marca> findByNome(String nome);
}
