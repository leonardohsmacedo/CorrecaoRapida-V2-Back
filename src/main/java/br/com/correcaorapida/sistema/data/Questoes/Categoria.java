package br.com.correcaorapida.sistema.data.Questoes;

import br.com.correcaorapida.sistema.data.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    private String nomeCategoria;

    //Junçao com a tabela de questoes
    @OneToMany(mappedBy = "categoria") // Carrega as questões sob demanda
    @JsonIgnore
    private List<Questao> questoes;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonBackReference
    private Usuario usuario;
}
