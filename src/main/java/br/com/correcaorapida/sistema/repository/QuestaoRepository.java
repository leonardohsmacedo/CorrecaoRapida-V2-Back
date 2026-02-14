package br.com.correcaorapida.sistema.repository;

import br.com.correcaorapida.sistema.data.Questoes.Questao;
import br.com.correcaorapida.sistema.data.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestaoRepository  extends JpaRepository<Questao, Long> {
    List<Questao> findByUsuario(Usuario usuario);
}
