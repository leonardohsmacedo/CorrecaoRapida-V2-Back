//package br.com.site.sistema.service;
//
////import br.com.site.sistema.data.RefreshToken;
//import br.com.site.sistema.repository.UsuarioRepository;
//import br.com.site.sistema.exception.exception.TokenRefreshException;
//import br.com.site.sistema.repository.RefreshTokenRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.Instant;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class RefreshTokenService {
//
//    @Value("${spring.app.jwtRefreshExpirationMs}")
//    private Long refreshTokenDurationMs;
//
//    @Autowired
//    private RefreshTokenRepository refreshTokenRepository;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    public Optional<RefreshToken> findByToken(String token) {
//        return refreshTokenRepository.findByToken(token);
//    }
//
//    public RefreshToken createRefreshToken(Long userId) {
//        Optional<RefreshToken> isAlreadyExist = refreshTokenRepository.findByUserId(userId);
//
//        isAlreadyExist.ifPresent(refreshToken -> refreshTokenRepository.delete(refreshToken));
//        RefreshToken refreshToken = new RefreshToken();
//        refreshToken.setUser(usuarioRepository.findById(userId).get());
//        refreshToken.setExpirationDate(Instant.now().plusMillis(refreshTokenDurationMs));
//        refreshToken.setToken(UUID.randomUUID().toString());
//
//        refreshToken = refreshTokenRepository.save(refreshToken);
//        return refreshToken;
//    }
//
//    public RefreshToken verifyExpiration(RefreshToken token) {
//        if (token.getExpirationDate().compareTo(Instant.now()) < 0) {
//            refreshTokenRepository.delete(token);
//            throw new TokenRefreshException(token.getToken(), "Refresh token was expired!. Please make a new signin request");
//        }
//
//        return token;
//    }
//
//    @Transactional
//    public int deleteByUserId(Long userId) {
//        return refreshTokenRepository.deleteByUser(usuarioRepository.findById(userId).get());
//    }
//
//}
