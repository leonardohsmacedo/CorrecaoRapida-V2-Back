package br.com.correcaorapida.sistema.service;

import br.com.correcaorapida.sistema.data.Questoes.Categoria;
import br.com.correcaorapida.sistema.data.Questoes.Questao;
import br.com.correcaorapida.sistema.data.Usuario.Usuario;
import br.com.correcaorapida.sistema.data.payload.requisicoes.CriarQuestaoObj;
import br.com.correcaorapida.sistema.repository.CategoriaRepository;
import br.com.correcaorapida.sistema.repository.QuestaoRepository;
import br.com.correcaorapida.sistema.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestaoService {

    QuestaoRepository questaoRepository;
    UsuarioRepository usuarioRepository;
    CategoriaRepository categoriaRepository;

    public void salvarQuestaoObj(CriarQuestaoObj criarQuestaoObj, UserDetails userDetails) {
        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername()).get();
        Categoria categoria = categoriaRepository.getReferenceById(criarQuestaoObj.idCategoria());

        Questao questao = new Questao();
        questao.setEnunciado(criarQuestaoObj.enunciado());
        questao.setResp1(criarQuestaoObj.resp1());
        questao.setResp2(criarQuestaoObj.resp2());
        questao.setResp3(criarQuestaoObj.resp3());
        questao.setResp4(criarQuestaoObj.resp4());
        questao.setResp5(criarQuestaoObj.resp5());
        questao.setRespCorreta(criarQuestaoObj.respostaCorreta());
        questao.setPublica(criarQuestaoObj.publica());
        questao.setCategoria(categoria);
        questao.setUsuario(usuario);

        questaoRepository.save(questao);

//        if(criarQuestaoObj.imagem)
//        questao.setImagem(criarQuestaoObj.imagem());

    }

    public Boolean salvarQuestaoDiss(CriarQuestaoObj criarQuestaoObj) {
        return null;
    }
}
