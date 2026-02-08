package br.com.correcaorapida.sistema.service;

import br.com.correcaorapida.sistema.data.payload.respostas.Dados.DadosUsuario;
import br.com.correcaorapida.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DadosUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public DadosUsuario buscaDados(String email) {
        var dados = usuarioRepository.findByUsername(email);
        return new DadosUsuario(dados.get().getNome());
    }
}
