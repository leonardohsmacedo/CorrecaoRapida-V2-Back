package br.com.correcaorapida.sistema.service;

import br.com.correcaorapida.sistema.data.DadosUsuario.Categoria;
import br.com.correcaorapida.sistema.data.payload.respostas.ListaCategoriasCriarQuestao;
import br.com.correcaorapida.sistema.repository.CategoriaRepository;
import br.com.correcaorapida.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<ListaCategoriasCriarQuestao> buscaCategoriasCriarQuestao(String username) {
        List<Categoria> categorias = usuarioRepository.findByUsername(username).get().getCategoriaList();

        List<ListaCategoriasCriarQuestao> listaCategoriasCriarQuestao = new ArrayList<>();

        for (Categoria value : categorias) {
            ListaCategoriasCriarQuestao categoria = new ListaCategoriasCriarQuestao(value.getIdCategoria(), value.getNomeCategoria());
            listaCategoriasCriarQuestao.add(categoria);
        }

        return listaCategoriasCriarQuestao;
    }
}
