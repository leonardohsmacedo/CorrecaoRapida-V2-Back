package br.com.correcaorapida.sistema.data.Questoes;

import br.com.correcaorapida.sistema.data.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "questoes_dissertativas")
@Data
public class QuestaoDissertativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuestaoDissertativa;

    @Column(length = 3000)
    private String enunciado;

    private Long numLinhas;

    private String imagem;

    private boolean publica;

    @ManyToOne
    @JoinColumn(name = "idCategoria") // Nome da coluna na tabela de questoes que será a chave estrangeira
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnore
    private Usuario usuario;
}
