package br.com.correcaorapida.sistema.data.Usuario;

import br.com.correcaorapida.sistema.data.Questoes.Categoria;
import br.com.correcaorapida.sistema.data.Questoes.QuestaoDissertativa;
import br.com.correcaorapida.sistema.data.Regras;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Usuario",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String username; // definido como email

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 150)
    private String senha;

    private String areaAtuacao;
    private String telefone;
    @Column(nullable = false)
    private String instituicao;
    private String emailCodigoVerificacao = String.valueOf(UUID.randomUUID());
    private boolean emailConfirmado;
    private boolean status; // para o painel admin -> usuario inativo eh q nao usou nos utlimos 30 dias
    private LocalDate dataProxProva;
    private boolean versaoPaga;
    private LocalDate versaoPagaExpiracao;
    //    private Long qtdProvasCorrigidasHj;
    //private Long qtdUsoDeIA = 0L;
    private LocalDateTime ultimoAcesso;

    @Column(nullable = false)
    private Boolean prefEmbaralharQuestoes = false;
    @Column(nullable = false)
    private Boolean prefEmbaralharAlternativas = true;


    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Categoria> categoriasList;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Categoria> categoriaList;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<QuestaoDissertativa> questaoDissertativaList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Regras> regras = new HashSet<>();

    /////////////////
    private boolean admin;
    private Long qtdMaxUsuariosGerenciados;
    private String adminInstituicao;
    @OneToMany(mappedBy = "gerente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UsuarioListaGerenciados> adminIdUsuariosGerenciados;
    /////////////////

    public Usuario(String username, String password) {
        this.username = username;
        this.senha = password;
    }

}
