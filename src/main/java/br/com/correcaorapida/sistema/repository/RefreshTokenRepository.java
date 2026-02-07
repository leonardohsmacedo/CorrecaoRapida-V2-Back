//package br.com.site.sistema.repository;
//
//import br.com.site.sistema.data.RefreshToken;
//import br.com.site.sistema.data.Usuario;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
//
//    Optional<RefreshToken> findByToken(String token);
//
//    Optional<RefreshToken> findByUserId(Long userId);
//
//
//    @Modifying
//    int deleteByUser(Usuario usuario);
//}
