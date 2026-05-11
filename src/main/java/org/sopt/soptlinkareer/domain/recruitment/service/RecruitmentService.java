package org.sopt.soptlinkareer.domain.recruitment.service;

import java.util.List;

import org.sopt.soptlinkareer.domain.recruitment.dto.request.RecruitmentSearchCondition;
import org.sopt.soptlinkareer.domain.recruitment.dto.response.RecruitmentResponse;
import org.sopt.soptlinkareer.domain.recruitment.repository.RecruitmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitmentService {

  private final RecruitmentRepository recruitmentRepository;

  public List<RecruitmentResponse> getRecruitments(RecruitmentSearchCondition condition) {
    return recruitmentRepository.findByCondition(condition).stream()
        .map(RecruitmentResponse::from)
        .toList();
  }
}
