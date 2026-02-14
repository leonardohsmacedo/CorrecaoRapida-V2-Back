package br.com.correcaorapida.sistema.service;

import br.com.correcaorapida.sistema.service.Groq.GroqService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IaService {

    String baseQuestaoObj = """
            {
              "enunciado": "enunciado da questão escolar/universitário",
              "resp1": "primeira resposta",
              "resp2": "segunda resposta",
              "resp3": "terceira resposta",
              "resp4": "quarta resposta",
              "resp5": "quinta resposta",
              "respostaCorreta": "resp4"
            }
            Você é um gerador de questões escolares/universitário para avaliação.
            Crie uma questão seguindo EXATAMENTE o formato JSON acima.
            Regras:
            1. Use múltipla escolha com um parágrafo único para o enunciado.
            2. Exatamente 5 opções de resposta.
            3. Opções NÃO numeradas e SEM letras.
            4. Responda APENAS o JSON puro, sem textos adicionais ou blocos de código (```).
            O tema/conteúdo da questão será:
            """;
    String baseQuestaoDiss = """
            { "enunciado": "enunciado da questão escolar/universitário" }
            Você é um gerador de questões escolares/universitário para avaliação.
            Crie uma questão seguindo EXATAMENTE o formato JSON acima.
            Regras:
            1. Um parágrafo único para o enunciado
            2. Responda APENAS o JSON puro, sem textos adicionais ou blocos de código (```).
            O tema/conteúdo da questão será:
            """;

    @Autowired
    GroqService groqService;

    public JsonNode criarQuestaoComIA(String tipo, String referencia) {
        if (tipo.equals("obj")) {
            String novoEnunciadoObj = baseQuestaoObj + referencia;
            return groqService.criarQuestaoComIA(novoEnunciadoObj);
        } else {
            String novoEnunciadoDiss = baseQuestaoDiss + referencia;
            return groqService.criarQuestaoComIA(novoEnunciadoDiss);
        }
    }
}
