package br.com.unicamp.Screematch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(@JsonAlias("Seasonhd") Integer numero,@JsonAlias("Episodes") List<DadosEpisodio> episodios) {
}
