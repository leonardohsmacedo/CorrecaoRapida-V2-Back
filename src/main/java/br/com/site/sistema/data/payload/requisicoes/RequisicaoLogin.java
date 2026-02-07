package br.com.site.sistema.data.payload.requisicoes;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequisicaoLogin {
    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
