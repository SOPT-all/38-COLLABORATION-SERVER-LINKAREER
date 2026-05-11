package org.sopt.soptlinkareer.domain.recruitment.repository;

import org.sopt.soptlinkareer.domain.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository
    extends JpaRepository<Recruitment, Long>, RecruitmentRepositoryCustom {}
