package br.com.correcaorapida.sistema.data.payload.respostas;

public record QuestoesDis(
        Long id,
        String enunciado,
        Long idCategoria
) {
}
