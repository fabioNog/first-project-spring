package br.com.unicamp.Screematch.service;

public interface IConverteDados {
    <T> T obterDados(String Json, Class<T> classe);
}
