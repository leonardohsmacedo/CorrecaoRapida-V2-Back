package br.com.correcaorapida.sistema.repository;

import br.com.correcaorapida.sistema.data.DadosUsuario.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
