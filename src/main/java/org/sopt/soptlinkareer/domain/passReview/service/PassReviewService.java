package org.sopt.soptlinkareer.domain.passReview.service;

import java.util.List;

import org.sopt.soptlinkareer.domain.passReview.dto.response.PassReviewResponse;
import org.sopt.soptlinkareer.domain.passReview.repository.PassReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PassReviewService {
  private final PassReviewRepository passReviewRepository;

  public List<PassReviewResponse> getPassReviews() {
    return passReviewRepository.findAll().stream().map(PassReviewResponse::from).toList();
  }
}
