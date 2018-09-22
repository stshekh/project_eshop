package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.AuditDTO;

import java.util.List;

public interface AuditService {
    AuditDTO save(AuditDTO auditDTO);

    AuditDTO update(AuditDTO auditDTO);

    List<AuditDTO> findAll();

    void delete(AuditDTO auditDTO);
}
