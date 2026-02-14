package br.com.correcaorapida.sistema.controllers;

import br.com.correcaorapida.sistema.data.payload.requisicoes.CriarQuestaoDiss;
import br.com.correcaorapida.sistema.data.payload.requisicoes.CriarQuestaoObj;
import br.com.correcaorapida.sistema.data.payload.requisicoes.DeletarQuestao;
import br.com.correcaorapida.sistema.data.payload.requisicoes.RequisicaoQuestaoComIA;
import br.com.correcaorapida.sistema.data.payload.respostas.QuestoesDis;
import br.com.correcaorapida.sistema.data.payload.respostas.QuestoesObj;
import br.com.correcaorapida.sistema.service.CategoriaService;
import br.com.correcaorapida.sistema.service.IaService;
import br.com.correcaorapida.sistema.service.QuestaoService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.DoubleToIntFunction;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("sistema/questao")
@AllArgsConstructor
public class QuestaoController {

    IaService iaService;
    QuestaoService questaoService;

    @PostMapping("ia")
    public ResponseEntity<JsonNode> questaoComIA(@Valid @RequestBody RequisicaoQuestaoComIA enunciado) {
//        Long qtdIa = questaoServico.controleUsoIA((UUID) sessao.getAttribute("idUsuario"));
//        if (qtdIa == 0) return null; else {
        try {
            JsonNode questaoIA = iaService.criarQuestaoComIA(enunciado.tipo(), enunciado.enunciado());
            return ResponseEntity.ok(questaoIA);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("salvar-obj")
    public ResponseEntity<String> salvarQuestaoObj(@Valid @RequestBody CriarQuestaoObj criarQuestaoObj, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            questaoService.salvarQuestaoObj(criarQuestaoObj, userDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body("Questão criada com sucesso!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("salvar-dis")
    public ResponseEntity<?> salvarQuestaoDiss(@RequestBody CriarQuestaoDiss criarQuestaoDiss, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            questaoService.salvarQuestaoDiss(criarQuestaoDiss, userDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body("Questão criada com sucesso!");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("lista-questoes-obj")
    public ResponseEntity<List<QuestoesObj>> questoesObj(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(questaoService.listarQuestoesObj(userDetails));
    }

    @GetMapping("lista-questoes-dis")
    public ResponseEntity<List<QuestoesDis>> questoesDis(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(questaoService.listarQuestoesDis(userDetails));
    }

    @PostMapping("deletar-questao")
    public ResponseEntity<String> deletarQuestao(@Valid @RequestBody DeletarQuestao deletarQuestao, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            if (questaoService.deletarQuestao(userDetails, deletarQuestao)) return ResponseEntity.ok("Deletado");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
