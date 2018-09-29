package com.gmail.sshekh.service.converter.impl.dto;

import com.gmail.sshekh.dao.model.Audit;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.converter.DTOConverter;
import com.gmail.sshekh.service.dto.AuditDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("auditDTOConverter")
public class AuditDTOConverter implements DTOConverter<Audit, AuditDTO> {
    @Autowired
    @Qualifier("userDTOConverter")
    private DTOConverter<User, UserDTO> userDTOConverter;

    @Override
    public AuditDTO toDTO(Audit entity) {
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setId(entity.getIdAudit());
        auditDTO.setCreated(entity.getCreated());
        auditDTO.setEventType(entity.getEventType());
        auditDTO.setUser(userDTOConverter.toDTO(entity.getUser()));
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