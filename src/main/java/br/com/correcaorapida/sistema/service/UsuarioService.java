package br.com.correcaorapida.sistema.service;

import br.com.correcaorapida.sistema.data.Usuario.Usuario;
import br.com.correcaorapida.sistema.data.payload.respostas.DadosUsuario;
import br.com.correcaorapida.sistema.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public DadosUsuario buscaDadosPainelGeral(String email) {
        Usuario dados = buscaUsuarioCompleto(email);
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

    public Usuario buscaUsuarioCompleto(String email){
        return usuarioRepository.findByUsername(email).get();
    }
}
