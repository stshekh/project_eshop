package com.gmail.sshekh.converter.impl.entity;

import com.gmail.sshekh.converter.Converter;
import com.gmail.sshekh.dao.model.Audit;
import com.gmail.sshekh.dto.AuditDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuditConverter implements Converter<AuditDTO, Audit> {
    @Override
    public Audit toEntity(AuditDTO dto) {
        Audit audit = new Audit();
        audit.setIdAudit(dto.getId());
        audit.setCreated(dto.getCreated());
        audit.setEventType(dto.getEventType());
        audit.setUser(new UserConverter().toEntity(dto.getUser()));
        return audit;
    }

    @Override
    public List<Audit> toEntityList(List<AuditDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public Set<Audit> toEntitySet(Set<AuditDTO> set) {
        return set.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
