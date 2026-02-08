package br.com.correcaorapida.sistema.controllers;

import br.com.correcaorapida.sistema.data.payload.respostas.Dados.DadosUsuario;
import br.com.correcaorapida.sistema.jwt.JwtUtils;
import br.com.correcaorapida.sistema.service.DadosUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("sistema")
public class DadosController {

    @Autowired
    DadosUsuarioService dadosUsuarioService;

    @GetMapping("dados")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<DadosUsuario> dadosUsuario(@AuthenticationPrincipal UserDetails userDetails) {
        return  ResponseEntity.ok(dadosUsuarioService.buscaDados(userDetails.getUsername()));
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
