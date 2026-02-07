package br.com.correcaorapida.sistema.controllers;

import br.com.correcaorapida.sistema.data.payload.requisicoes.RequisicaoCadastro;
import br.com.correcaorapida.sistema.data.payload.requisicoes.RequisicaoLogin;
import br.com.correcaorapida.sistema.data.payload.respostas.JwtResposta;
import br.com.correcaorapida.sistema.exception.exception.SignInException;
import br.com.correcaorapida.sistema.data.Usuario;
import br.com.correcaorapida.sistema.jwt.JwtUtils;
import br.com.correcaorapida.sistema.repository.RegrasRepository;
import br.com.correcaorapida.sistema.repository.UsuarioRepository;
import br.com.correcaorapida.sistema.service.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RestController
    @RequestMapping("sistema")
    @AllArgsConstructor
    public class AutenticacaoController {

    AuthenticationManager authenticationManager;
    UsuarioRepository usuarioRepository;
    RegrasRepository regrasRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;
//    RefreshTokenService refreshTokenService;

    @PostMapping("criarconta")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RequisicaoCadastro requisicaoCadastro) {

        if (usuarioRepository.existsByEmail(requisicaoCadastro.getEmail())) {
            return ResponseEntity.badRequest().body("Erro: O endereço de e-mail já está em uso!");
        }

        Usuario usuario = new Usuario(requisicaoCadastro.getEmail(),
                encoder.encode(requisicaoCadastro.getSenha()));

        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    @PostMapping("login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody RequisicaoLogin requisicaoLogin) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(requisicaoLogin.getEmail(), requisicaoLogin.getSenha()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//            List<String> roles = userDetails.getAuthorities().stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResposta(
                    jwt,
                    userDetails.getEmail()
            ));
        } catch (AuthenticationException exception) {
            throw new SignInException(exception.getMessage());
        }

    }

}
