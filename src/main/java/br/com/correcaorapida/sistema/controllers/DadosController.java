package br.com.correcaorapida.sistema.controllers;

import br.com.correcaorapida.sistema.data.payload.requisicoes.RequisicaoQuestaoComIA;
import br.com.correcaorapida.sistema.data.payload.respostas.DadosUsuario;
import br.com.correcaorapida.sistema.data.payload.respostas.ListaCategoriasCriarQuestao;
import br.com.correcaorapida.sistema.service.CategoriaService;
import br.com.correcaorapida.sistema.service.IaService;
import br.com.correcaorapida.sistema.service.UsuarioService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("sistema")
public class DadosController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("dados")
    // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<DadosUsuario> dadosUsuario(@AuthenticationPrincipal UserDetails userDetails) {
//        System.out.println("e-mail: " + userDetails.getUsername());
        return ResponseEntity.ok(usuarioService.buscaDadosPainelGeral(userDetails.getUsername()));
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
