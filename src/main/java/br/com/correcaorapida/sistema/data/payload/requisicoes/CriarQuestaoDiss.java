package br.com.correcaorapida.sistema.data.payload.requisicoes;

public record CriarQuestaoDiss (
        String enunciado,
        String respostaCorreta,
        Long idCategoria
){
}
