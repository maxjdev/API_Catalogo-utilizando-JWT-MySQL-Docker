package br.com.catalog.services;

public interface IAdmService<T> {
    void disable(Long id) throws Exception;
    T activate(Long id) throws Exception;
    void fullDelete(Long id) throws Exception;
}
