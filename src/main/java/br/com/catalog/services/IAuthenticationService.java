package br.com.catalog.services;

public interface IAuthenticationService<T, R, V> {
    String login(T dto);
    R register(V dto);
}
