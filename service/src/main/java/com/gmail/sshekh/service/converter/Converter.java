package com.gmail.sshekh.service.converter;

import java.util.List;
import java.util.Set;

public interface Converter<D, E> {
    E toEntity(D dto);

    List<E> toEntityList(List<D> list);

    Set<E> toEntitySet(Set<D> set);
}
