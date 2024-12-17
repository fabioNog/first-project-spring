package br.com.unicamp.Screematch;

import br.com.unicamp.Screematch.model.DadosEpisodio;
import br.com.unicamp.Screematch.model.DadosSerie;
import br.com.unicamp.Screematch.model.DadosTemporada;
import br.com.unicamp.Screematch.service.ConsumoApi;
import br.com.unicamp.Screematch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreematchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreematchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&Season&apikey=2173c5ab");

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados  =  conversor.obterDados(json, DadosSerie.class);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for(int i = 1;i<=dados.totalTemporadas();i++){
			json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season=" + i +"&apikey=2173c5ab");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

		temporadas.forEach(System.out::println);

	}
}
