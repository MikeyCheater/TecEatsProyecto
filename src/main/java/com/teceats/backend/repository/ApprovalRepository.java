package com.teceats.backend.repository;

import com.teceats.backend.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
}
