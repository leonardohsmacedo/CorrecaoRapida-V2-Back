package br.com.correcaorapida.sistema.repository;

import br.com.correcaorapida.sistema.data.Questoes.Categoria;
import br.com.correcaorapida.sistema.data.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNomeCategoriaAndUsuario(String nome, Usuario usuario);
}
