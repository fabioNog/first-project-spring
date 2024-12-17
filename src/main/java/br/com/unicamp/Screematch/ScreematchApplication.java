package br.com.unicamp.Screematch;

import br.com.unicamp.Screematch.model.DadosTemporada;
import br.com.unicamp.Screematch.principal.Principal;
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
		Principal principal = new Principal();
		principal.exibeMenu();

		List<DadosTemporada> temporadas = new ArrayList<>();

	/*	for(int i = 1;i<=dados.totalTemporadas();i++){
			json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season=" + i +"&apikey=2173c5ab");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

		temporadas.forEach(System.out::println);*/

	}
}
