package com.gmail.sshekh.service.converter;

import java.util.List;
import java.util.Set;

public interface DTOConverter<E, D> {
    D toDTO(E entity);

    List<D> toDTOList(List<E> list);

    Set<D> toDTOSet(Set<E> set);
}
