package br.com.correcaorapida.sistema.controllers;

import br.com.correcaorapida.sistema.data.payload.requisicoes.CriarQuestaoDiss;
import br.com.correcaorapida.sistema.data.payload.requisicoes.CriarQuestaoObj;
import br.com.correcaorapida.sistema.data.payload.requisicoes.RequisicaoQuestaoComIA;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("sistema/questao")
@AllArgsConstructor
public class QuestaoController {

    IaService iaService;
    CategoriaService categoriaService;
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
        questaoService.salvarQuestaoObj(criarQuestaoObj, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body("Questão criada com sucesso!");
    }

    @PostMapping("salvar-dis")
    public ResponseEntity<?> salvarQuestaoDiss(@RequestBody CriarQuestaoDiss criarQuestaoDiss, @AuthenticationPrincipal UserDetails userDetails) {
        questaoService.salvarQuestaoDiss(criarQuestaoDiss, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body("Questão criada com sucesso!");
    }
}
