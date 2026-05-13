package org.sopt.soptlinkareer.domain.passReview.controller;

import java.util.List;

import org.sopt.soptlinkareer.domain.passReview.dto.response.PassReviewResponse;
import org.sopt.soptlinkareer.domain.passReview.exception.PassReviewSuccessCode;
import org.sopt.soptlinkareer.domain.passReview.service.PassReviewService;
import org.sopt.soptlinkareer.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "PassReview", description = "합격 후기 API")
@RestController
@RequestMapping("/api/v1/pass-reviews")
@RequiredArgsConstructor
public class PassReviewController {

  private final PassReviewService passReviewService;

  @Operation(summary = "합격 후기 전체 조회", description = "등록된 합격 후기 전체 목록을 조회합니다.")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "합격 후기 조회 성공")})
  @GetMapping()
  public ResponseEntity<BaseResponse<List<PassReviewResponse>>> getReviews() {
    List<PassReviewResponse> response = passReviewService.getPassReviews();
    return ResponseEntity.ok(
        BaseResponse.onSuccess(PassReviewSuccessCode.GET_REVIEWS_SUCCESS, response));
  }
}
