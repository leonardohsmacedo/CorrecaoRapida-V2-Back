package br.com.correcaorapida.sistema.data.DadosUsuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioListaGerenciados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // pessoa
    private UUID identificador;

    //gerente
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario gerente;

}
