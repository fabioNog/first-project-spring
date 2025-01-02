package br.com.unicamp.Screematch.principal;

import br.com.unicamp.Screematch.model.DadosEpisodio;
import br.com.unicamp.Screematch.model.DadosSerie;
import br.com.unicamp.Screematch.model.DadosTemporada;
import br.com.unicamp.Screematch.model.Episodio;
import br.com.unicamp.Screematch.service.ConsumoApi;
import br.com.unicamp.Screematch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        //temporadas.forEach(System.out::println);

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("Top 5 Episódios");

        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed()).
                limit(5).forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream().map(d -> new Episodio(t.numero(),d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("A partir de que ano você deseja ver os episodios?");
        var ano = leitura.nextInt();
        leitura.nextLine();

        LocalDate dataBusca = LocalDate.of(ano,1,1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getDataLancamento() != null &&  e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                " Episódio: " + e.getTitulo() +
                                " Data lançamento: " + e.getDataLancamento().format(formatador)
                ));

    }
}
