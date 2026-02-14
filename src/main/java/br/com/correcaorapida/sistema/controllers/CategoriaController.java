package br.com.correcaorapida.sistema.controllers;

import br.com.correcaorapida.sistema.data.payload.requisicoes.CriarCategoria;
import br.com.correcaorapida.sistema.data.payload.respostas.CategoriasDoUsuario;
import br.com.correcaorapida.sistema.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("sistema/categorias")
@AllArgsConstructor
public class CategoriaController {

    //    IaService iaService;
    CategoriaService categoriaService;
//    QuestaoService questaoService;

    @GetMapping("lista-categorias")
    public ResponseEntity<List<CategoriasDoUsuario>> litaCategorias(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(categoriaService.buscaListaCategorias(userDetails.getUsername()));
    }

    @PostMapping("cadastrar-categoria")
    public ResponseEntity<CategoriasDoUsuario> cadastrarCategoria(@Valid @RequestBody CriarCategoria nome, @AuthenticationPrincipal UserDetails userDetails) {
        if (categoriaService.checaCategoriaExiste(nome.nome(), userDetails) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            CategoriasDoUsuario categoriasDoUsuario = categoriaService.salvarCategoria(nome.nome(), userDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriasDoUsuario);
        }
    }
}
