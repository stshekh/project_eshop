package com.gmail.sshekh.converter.impl.dto;

import com.gmail.sshekh.converter.DTOConverter;
import com.gmail.sshekh.dao.model.Audit;
import com.gmail.sshekh.dto.AuditDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuditDTOConverter implements DTOConverter<Audit, AuditDTO> {
    @Override
    public AuditDTO toDTO(Audit entity) {
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setId(entity.getIdAudit());
        auditDTO.setCreated(entity.getCreated());
        auditDTO.setEventType(entity.getEventType());
        auditDTO.setUser(new UserDTOConverter().toDTO(entity.getUser()));
        return auditDTO;
    }

    @Override
    public List<AuditDTO> toDTOList(List<Audit> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Set<AuditDTO> toDTOSet(Set<Audit> set) {
        return set.stream().map(this::toDTO).collect(Collectors.toSet());
    }
}
