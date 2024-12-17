package br.com.unicamp.Screematch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(
        @JsonProperty("Title") String titulo,
        @JsonProperty("imdbRating") String avaliacao,
        @JsonProperty("totalSeasons") String totalSeasons) {

    public Integer totalTemporadas() {
        // Converte "N/A" para null ou mantém o valor original
        try {
            return totalSeasons.equals("N/A") ? null : Integer.parseInt(totalSeasons);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
