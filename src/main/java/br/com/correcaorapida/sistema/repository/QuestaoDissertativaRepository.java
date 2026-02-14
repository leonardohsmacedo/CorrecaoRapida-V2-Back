package br.com.correcaorapida.sistema.repository;

import br.com.correcaorapida.sistema.data.Questoes.QuestaoDissertativa;
import br.com.correcaorapida.sistema.data.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestaoDissertativaRepository extends JpaRepository<QuestaoDissertativa, Long> {
    List<QuestaoDissertativa> findByUsuario(Usuario usuario);
}
