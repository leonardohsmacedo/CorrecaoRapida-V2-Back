package br.com.correcaorapida.sistema.data.payload.requisicoes;

public record CriarQuestaoDiss (
        String enunciado,
        Long idCategoria,
        Boolean publica,
        Long numLinhas
){
}
