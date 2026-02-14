package br.com.correcaorapida.sistema.data.Questoes;

import br.com.correcaorapida.sistema.data.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "questoes")
@Data
@NoArgsConstructor
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuestao;

    @Column(length = 3000)  // Definindo o tamanho máximo do enunciado como 500 caracteres
    private String enunciado;

    @Column(length = 2000)
    private String resp1;

    @Column(length = 2000)
    private String resp2;

    @Column(length = 2000)
    private String resp3;

    @Column(length = 2000)
    private String resp4;

    @Column(length = 2000)
    private String resp5;

    @Column(length = 2000)
    private String respCorreta;

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
