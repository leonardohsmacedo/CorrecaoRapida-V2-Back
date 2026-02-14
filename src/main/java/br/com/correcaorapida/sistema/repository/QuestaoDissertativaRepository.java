package br.com.correcaorapida.sistema.repository;

import br.com.correcaorapida.sistema.data.Questoes.QuestaoDissertativa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestaoDissertativaRepository extends JpaRepository<QuestaoDissertativa, Long> {
}
