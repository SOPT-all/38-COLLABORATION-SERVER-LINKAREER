package org.sopt.soptlinkareer.domain.passReview.dto.response;

import java.time.LocalDateTime;

import org.sopt.soptlinkareer.domain.passReview.entity.PassReview;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

public record PassReviewResponse(
    @Schema(description = "후기 ID") Long id,
    @Schema(description = "후기 제목") String title,
    @Schema(description = "후기 내용") String content,
    @Schema(description = "작성일") @JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime createdAt) {
  public static PassReviewResponse from(PassReview review) {
    return new PassReviewResponse(
        review.getId(), review.getTitle(), review.getContent(), review.getCreatedAt());
  }
}
