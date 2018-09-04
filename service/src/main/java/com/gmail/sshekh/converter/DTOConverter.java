package com.gmail.sshekh.converter;

import java.util.List;

public interface DTOConverter<E, D> {
    D toDTO(E entity);

    List<D> toDTOList(List<E> list);
}
