package com.example.carserviceapp.dto.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
