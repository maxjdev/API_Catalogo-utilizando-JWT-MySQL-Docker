package br.com.catalog.services;

import br.com.catalog.controller.responses.PaginationResponse;

public interface ICrudService<T, R> {
    T create(T dto);
    T  findById(Long id) throws Exception;
    T findByName(String name)throws Exception;
    T update(Long id, R dto) throws Exception;
    void delete(Long id) throws Exception;
    PaginationResponse<T> findAll(int page, int size);
}
