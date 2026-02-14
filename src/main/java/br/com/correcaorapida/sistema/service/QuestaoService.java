package br.com.correcaorapida.sistema.service;

import br.com.correcaorapida.sistema.data.Questoes.Categoria;
import br.com.correcaorapida.sistema.data.Questoes.Questao;
import br.com.correcaorapida.sistema.data.Questoes.QuestaoDissertativa;
import br.com.correcaorapida.sistema.data.Usuario.Usuario;
import br.com.correcaorapida.sistema.data.payload.requisicoes.CriarQuestaoDiss;
import br.com.correcaorapida.sistema.data.payload.requisicoes.CriarQuestaoObj;
import br.com.correcaorapida.sistema.data.payload.requisicoes.DeletarQuestao;
import br.com.correcaorapida.sistema.data.payload.respostas.QuestoesDis;
import br.com.correcaorapida.sistema.data.payload.respostas.QuestoesObj;
import br.com.correcaorapida.sistema.repository.CategoriaRepository;
import br.com.correcaorapida.sistema.repository.QuestaoDissertativaRepository;
import br.com.correcaorapida.sistema.repository.QuestaoRepository;
import br.com.correcaorapida.sistema.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestaoService {

    QuestaoRepository questaoRepository;
    QuestaoDissertativaRepository questaoDissertativaRepository;
    UsuarioRepository usuarioRepository;
    CategoriaRepository categoriaRepository;
    UsuarioService usuarioService;

    public void salvarQuestaoObj(CriarQuestaoObj criarQuestaoObj, UserDetails userDetails) {
        try {  // retirar o try catch
            Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername()).get();
            Categoria categoria = categoriaRepository.getReferenceById(criarQuestaoObj.idCategoria());

            String textoRespostaCorreta = "";

            int posicaoRespCorreta = Integer.parseInt(criarQuestaoObj.respostaCorreta());

            switch (posicaoRespCorreta) {
                case 1 -> textoRespostaCorreta = criarQuestaoObj.resp1();
                case 2 -> textoRespostaCorreta = criarQuestaoObj.resp2();
                case 3 -> textoRespostaCorreta = criarQuestaoObj.resp3();
                case 4 -> textoRespostaCorreta = criarQuestaoObj.resp4();
                case 5 -> textoRespostaCorreta = criarQuestaoObj.resp5();
            }

            Questao questao = new Questao();
            questao.setEnunciado(criarQuestaoObj.enunciado());
            questao.setResp1(criarQuestaoObj.resp1());
            questao.setResp2(criarQuestaoObj.resp2());
            questao.setResp3(criarQuestaoObj.resp3());
            questao.setResp4(criarQuestaoObj.resp4());
            questao.setResp5(criarQuestaoObj.resp5());
            questao.setRespCorreta(textoRespostaCorreta);
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

    public List<QuestoesObj> listarQuestoesObj(UserDetails userDetails) {
        Usuario usuario = usuarioService.buscaUsuarioCompleto(userDetails.getUsername());
        List<Questao> questoes = questaoRepository.findByUsuario(usuario);

        List<QuestoesObj> questoesObjList = new ArrayList<>();

        for (Questao q : questoes) {
            QuestoesObj questoesObj = new QuestoesObj(q.getIdQuestao(), q.getEnunciado(), q.getResp1(), q.getResp2(), q.getResp3(), q.getResp4(), q.getResp5(), q.getRespCorreta(), q.getCategoria().getIdCategoria());
            questoesObjList.add(questoesObj);
        }

        return questoesObjList;
    }

    public List<QuestoesDis> listarQuestoesDis(UserDetails userDetails) {
        Usuario usuario = usuarioService.buscaUsuarioCompleto(userDetails.getUsername());
        List<QuestaoDissertativa> questoes = questaoDissertativaRepository.findByUsuario(usuario);

        List<QuestoesDis> questoesDisList = new ArrayList<>();

        for (QuestaoDissertativa q : questoes) {
            QuestoesDis questaoDis = new QuestoesDis(q.getIdQuestaoDissertativa(), q.getEnunciado(), q.getCategoria().getIdCategoria());
            questoesDisList.add(questaoDis);
        }

        return questoesDisList;
    }

    public boolean deletarQuestao(UserDetails userDetails, DeletarQuestao deletarQuestao) {
        String email = usuarioService.buscaUsuarioCompleto(userDetails.getUsername()).getUsername();

        if (deletarQuestao.tipo().equals("obj")) {
            Optional<Questao> questao = questaoRepository.findById(deletarQuestao.id());
            if (questao.get().getUsuario().getUsername().equals(email)) {
                questaoRepository.deleteById(deletarQuestao.id());
                return true;
            } else return false;
        } else {
            Optional<QuestaoDissertativa> questaoDissertativa = questaoDissertativaRepository.findById(deletarQuestao.id());
            if (questaoDissertativa.get().getUsuario().getUsername().equals(email)) {
                questaoDissertativaRepository.deleteById(deletarQuestao.id());
                return true;
            } else return false;
        }
    }
}
