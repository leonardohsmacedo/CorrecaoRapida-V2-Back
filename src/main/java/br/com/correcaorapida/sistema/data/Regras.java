package br.com.correcaorapida.sistema.data;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "regras")
@Data
public class Regras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERegras nome;

}
