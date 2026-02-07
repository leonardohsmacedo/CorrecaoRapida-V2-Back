package br.com.site.sistema.repository;

import br.com.site.sistema.data.ERegras;
import br.com.site.sistema.data.Regras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegrasRepository extends JpaRepository<Regras, Long> {

    Optional<Regras> findByNome(ERegras nome);

}
