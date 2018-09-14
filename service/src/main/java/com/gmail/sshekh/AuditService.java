package com.gmail.sshekh;

import com.gmail.sshekh.dto.AuditDTO;

import java.util.List;

public interface AuditService {
    AuditDTO save(AuditDTO auditDTO);

    AuditDTO update(AuditDTO auditDTO);

    List<AuditDTO> findAll();

    void delete(AuditDTO auditDTO);
}
