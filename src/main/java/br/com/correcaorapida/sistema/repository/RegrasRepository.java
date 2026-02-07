package br.com.correcaorapida.sistema.repository;

import br.com.correcaorapida.sistema.data.ERegras;
import br.com.correcaorapida.sistema.data.Regras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegrasRepository extends JpaRepository<Regras, Long> {

    Optional<Regras> findByNome(ERegras nome);

}
