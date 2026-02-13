package br.com.correcaorapida.sistema.repository;

import br.com.correcaorapida.sistema.data.DadosUsuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<Usuario> findByEmailCodigoVerificacao(String codigo);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.ultimoAcesso = :data WHERE u.username = :email")
    int atualizarUltimoAcesso(String email, LocalDateTime data);

}
