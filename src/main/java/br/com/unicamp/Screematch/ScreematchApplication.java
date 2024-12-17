package br.com.unicamp.Screematch;

import br.com.unicamp.Screematch.model.DadosSerie;
import br.com.unicamp.Screematch.service.ConsumoApi;
import br.com.unicamp.Screematch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreematchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreematchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("http://www.omdbapi.com/?t=matrix&Season&apikey=2173c5ab");
		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados  =  conversor.obterDados(json, DadosSerie.class);

		System.out.println(dados);
	}
}
