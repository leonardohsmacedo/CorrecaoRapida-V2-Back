package br.com.correcaorapida.sistema.service;

import br.com.correcaorapida.sistema.data.DadosUsuario.Usuario;
import br.com.correcaorapida.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Usuario usuario = usuarioRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o nome este email: " + username));

        Usuario usuario = usuarioRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o nome este usuario: " + username));

        return UserDetailsImpl.build(usuario);
    }
}
