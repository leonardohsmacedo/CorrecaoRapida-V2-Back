package br.com.correcaorapida.sistema.data.payload.requisicoes;

public record CriarQuestaoObj(
        String enunciado,
        String resp1,
        String resp2,
        String resp3,
        String resp4,
        String resp5,
        String respostaCorreta,
        Long idCategoria,
        Boolean publica

) {
}
