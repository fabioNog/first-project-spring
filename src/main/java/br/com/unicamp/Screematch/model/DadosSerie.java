package br.com.unicamp.Screematch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(
        @JsonProperty("Title") String titulo,
        @JsonProperty("imdbRating") String avaliacao,
        @JsonProperty("totalSeasons") String totalTemporadas) {

    public Integer buscaTotalTemporadas() {
        // Converte "N/A" para null ou mantém o valor original
        try {
            return totalTemporadas != null && !totalTemporadas.equalsIgnoreCase("N/A")
                    ? Integer.parseInt(totalTemporadas)
                    : 0;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
