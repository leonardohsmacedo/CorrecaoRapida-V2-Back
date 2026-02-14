package br.com.correcaorapida.sistema.repository;

import br.com.correcaorapida.sistema.data.Questoes.Questao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestaoRepository  extends JpaRepository<Questao, Long> {
}
