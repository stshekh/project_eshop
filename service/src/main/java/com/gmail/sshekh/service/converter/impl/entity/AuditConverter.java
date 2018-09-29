package com.gmail.sshekh.service.converter.impl.entity;

import com.gmail.sshekh.dao.model.Audit;
import com.gmail.sshekh.dao.model.User;
import com.gmail.sshekh.service.converter.Converter;
import com.gmail.sshekh.service.dto.AuditDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("auditConverter")
public class AuditConverter implements Converter<AuditDTO, Audit> {
    @Autowired
    @Qualifier("userConverter")
    private Converter<UserDTO, User> userConverter;

    @Override
    public Audit toEntity(AuditDTO dto) {
        Audit audit = new Audit();
        audit.setIdAudit(dto.getId());
        audit.setCreated(dto.getCreated());
        audit.setEventType(dto.getEventType());
        audit.setUser(userConverter.toEntity(dto.getUser()));
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
