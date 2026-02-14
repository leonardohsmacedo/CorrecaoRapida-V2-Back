package br.com.correcaorapida.sistema.service;

import br.com.correcaorapida.sistema.data.Questoes.Categoria;
import br.com.correcaorapida.sistema.data.Usuario.Usuario;
import br.com.correcaorapida.sistema.data.payload.respostas.CategoriasDoUsuario;
import br.com.correcaorapida.sistema.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    UsuarioService usuarioService;

    public List<CategoriasDoUsuario> buscaListaCategorias(String username) {
        List<Categoria> categorias = usuarioService.buscaUsuaioCompleto(username).getCategoriasList();
        List<CategoriasDoUsuario> categoriaDoUsuarios = new ArrayList<>();

        for (Categoria value : categorias) {
            CategoriasDoUsuario categoria = new CategoriasDoUsuario(value.getIdCategoria(), value.getNomeCategoria());
            categoriaDoUsuarios.add(categoria);
        }

        return categoriaDoUsuarios;
    }

    public String checaCategoriaExiste(String nomeCategoria, UserDetails userDetails) {
        Usuario usuario = usuarioService.buscaUsuaioCompleto(userDetails.getUsername());

        Optional<Categoria> cat = categoriaRepository.findByNomeCategoriaAndUsuario(nomeCategoria, usuario);

        if (cat.isPresent()) return "Essa categoria já existe!";
        else return null;
    }

    public CategoriasDoUsuario salvarCategoria(String nomeCategoria, UserDetails userDetails) {

        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(nomeCategoria);
        categoria.setUsuario(usuarioService.buscaUsuaioCompleto(userDetails.getUsername()));

        var dados = categoriaRepository.save(categoria);

        return new CategoriasDoUsuario(dados.getIdCategoria(), dados.getNomeCategoria());
    }
}
