package br.com.correcaorapida.sistema.controllers;

import br.com.correcaorapida.sistema.data.payload.requisicoes.RequisicaoQuestaoComIA;
import br.com.correcaorapida.sistema.data.payload.respostas.Dados.DadosUsuario;
import br.com.correcaorapida.sistema.service.IaService;
import br.com.correcaorapida.sistema.service.UsuarioService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.naming.ServiceUnavailableException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("sistema")
public class DadosController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    IaService iaService;

    @GetMapping("dados")
    // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<DadosUsuario> dadosUsuario(@AuthenticationPrincipal UserDetails userDetails) {
//        System.out.println("e-mail: " + userDetails.getUsername());
        return ResponseEntity.ok(usuarioService.buscaDados(userDetails.getUsername()));
    }

    @PostMapping("qia")
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

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
