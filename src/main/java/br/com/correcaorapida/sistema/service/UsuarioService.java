package br.com.correcaorapida.sistema.service;

import br.com.correcaorapida.sistema.data.DadosUsuario.Usuario;
import br.com.correcaorapida.sistema.data.payload.respostas.Dados.DadosUsuario;
import br.com.correcaorapida.sistema.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public DadosUsuario buscaDados(String email) {
        var dados = usuarioRepository.findByUsername(email).get();
        return new DadosUsuario(dados.getNome(), dados.getInstituicao());
    }

    @Async
    public void atualizaUltimoLogin(String email) {
        usuarioRepository.atualizarUltimoAcesso(email, LocalDateTime.now());
    }

    @Transactional
    public boolean confirmarEmail(String codigo) {
        return usuarioRepository.findByEmailCodigoVerificacao(codigo)
                .map(usuario -> {
                    usuario.setEmailCodigoVerificacao(null);
                    usuario.setEmailConfirmado(true); // Boa prática: ter um flag de status
                    usuarioRepository.save(usuario);
                    return true;
                }).orElse(false);
    }
}
