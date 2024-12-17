package br.com.unicamp.Screematch.principal;

import br.com.unicamp.Screematch.model.DadosSerie;
import br.com.unicamp.Screematch.service.ConsumoApi;
import br.com.unicamp.Screematch.service.ConverteDados;

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
        System.out.println(json);
        DadosSerie dados  =  conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
    }
}
