package com.example.carserviceapp.dto.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
