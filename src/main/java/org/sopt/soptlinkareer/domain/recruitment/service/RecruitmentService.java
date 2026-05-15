package org.sopt.soptlinkareer.domain.recruitment.service;

import java.util.List;

import org.sopt.soptlinkareer.domain.recruitment.dto.request.RecruitmentSearchCondition;
import org.sopt.soptlinkareer.domain.recruitment.dto.response.RecruitmentDetailResponse;
import org.sopt.soptlinkareer.domain.recruitment.dto.response.RecruitmentResponse;
import org.sopt.soptlinkareer.domain.recruitment.entity.Recruitment;
import org.sopt.soptlinkareer.domain.recruitment.exception.RecruitmentErrorCode;
import org.sopt.soptlinkareer.domain.recruitment.repository.RecruitmentRepository;
import org.sopt.soptlinkareer.global.exception.CustomException;
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

  public RecruitmentDetailResponse getRecruitmentDetail(Long recruitmentId) {
    Recruitment recruitment =
        recruitmentRepository
            .findById(recruitmentId)
            .orElseThrow(() -> new CustomException(RecruitmentErrorCode.RECRUITMENT_NOT_FOUND));
    return RecruitmentDetailResponse.from(recruitment);
  }
}
