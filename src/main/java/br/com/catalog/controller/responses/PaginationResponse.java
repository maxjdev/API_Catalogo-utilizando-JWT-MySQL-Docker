package br.com.catalog.controller.responses;

import org.springframework.data.domain.Page;

import java.util.List;

public record PaginationResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
    public static <T> PaginationResponse<T> pagination(Page<T> page) {
        return new PaginationResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
