package br.com.unicamp.Screematch.principal;

import br.com.unicamp.Screematch.model.DadosSerie;
import br.com.unicamp.Screematch.model.DadosTemporada;
import br.com.unicamp.Screematch.service.ConsumoApi;
import br.com.unicamp.Screematch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    //CONSTANTES
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=2173c5ab";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    // MAIN FUNCTION
    public void exibeMenu(){
        System.out.println("Digite o nome da Serie");
        var nomeSerie = leitura.nextLine();

        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
        DadosSerie dados  =  conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1;i<=dados.buscaTotalTemporadas();i++){
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ","+") + "&season="  + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(System.out::println);

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}
