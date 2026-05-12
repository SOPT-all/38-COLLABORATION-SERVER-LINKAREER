package org.sopt.soptlinkareer.domain.featuredrecruitment.service;

import java.util.List;

import org.sopt.soptlinkareer.domain.featuredrecruitment.dto.response.FeaturedRecruitmentResponse;
import org.sopt.soptlinkareer.domain.featuredrecruitment.repository.FeaturedRecruitmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeaturedRecruitmentService {

  private final FeaturedRecruitmentRepository featuredRecruitmentRepository;

  public List<FeaturedRecruitmentResponse> getFeaturedRecruitments() {
    return featuredRecruitmentRepository.findAll().stream()
        .map(FeaturedRecruitmentResponse::from)
        .toList();
  }
}
