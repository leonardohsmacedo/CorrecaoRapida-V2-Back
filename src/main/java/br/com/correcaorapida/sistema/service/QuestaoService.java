package br.com.correcaorapida.sistema.service;

import br.com.correcaorapida.sistema.data.Questoes.Categoria;
import br.com.correcaorapida.sistema.data.Questoes.Questao;
import br.com.correcaorapida.sistema.data.Questoes.QuestaoDissertativa;
import br.com.correcaorapida.sistema.data.Usuario.Usuario;
import br.com.correcaorapida.sistema.data.payload.requisicoes.CriarQuestaoDiss;
import br.com.correcaorapida.sistema.data.payload.requisicoes.CriarQuestaoObj;
import br.com.correcaorapida.sistema.repository.CategoriaRepository;
import br.com.correcaorapida.sistema.repository.QuestaoDissertativaRepository;
import br.com.correcaorapida.sistema.repository.QuestaoRepository;
import br.com.correcaorapida.sistema.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestaoService {

    QuestaoRepository questaoRepository;
    QuestaoDissertativaRepository questaoDissertativaRepository;
    UsuarioRepository usuarioRepository;
    CategoriaRepository categoriaRepository;

    public void salvarQuestaoObj(CriarQuestaoObj criarQuestaoObj, UserDetails userDetails) {
        try {  // retirar o try catch
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
//        if(criarQuestaoObj.imagem)
//        questao.setImagem(criarQuestaoObj.imagem());
            questaoRepository.save(questao);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void salvarQuestaoDiss(CriarQuestaoDiss criarQuestaoDiss, UserDetails userDetails) {
        try {  // retirar o try catch
            Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername()).get();
            Categoria categoria = categoriaRepository.getReferenceById(criarQuestaoDiss.idCategoria());

            QuestaoDissertativa questaoDissertativa = new QuestaoDissertativa();
            questaoDissertativa.setEnunciado(criarQuestaoDiss.enunciado());
            questaoDissertativa.setCategoria(categoria);
            questaoDissertativa.setPublica(criarQuestaoDiss.publica());
            questaoDissertativa.setNumLinhas(criarQuestaoDiss.numLinhas());
            questaoDissertativa.setUsuario(usuario);
//      if imagem
//      questaoDissertativa.setImagem(criarQuestaoDiss.imagem());
            questaoDissertativaRepository.save(questaoDissertativa);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
