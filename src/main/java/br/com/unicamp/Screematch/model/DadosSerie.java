package br.com.unicamp.Screematch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(
        @JsonProperty("Title") String titulo,
        @JsonProperty("imdbRating") String avaliacao,
        @JsonProperty("totalSeasons") String totalTemporadas) {

    public Integer totalTemporadas() {
        // Converte "N/A" para null ou mantém o valor original
        try {
            return totalSeasons != null && !totalSeasons.equalsIgnoreCase("N/A")
                    ? Integer.parseInt(totalSeasons)
                    : 0;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
