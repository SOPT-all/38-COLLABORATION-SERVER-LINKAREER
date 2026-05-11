package org.sopt.soptlinkareer.domain.recruitment.repository;

import java.util.List;

import org.sopt.soptlinkareer.domain.recruitment.dto.request.RecruitmentSearchCondition;
import org.sopt.soptlinkareer.domain.recruitment.entity.Recruitment;

public interface RecruitmentRepositoryCustom {

  List<Recruitment> findByCondition(RecruitmentSearchCondition condition);
}
