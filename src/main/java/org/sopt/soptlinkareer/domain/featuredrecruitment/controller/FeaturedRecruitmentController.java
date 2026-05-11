package org.sopt.soptlinkareer.domain.featuredrecruitment.controller;

import java.util.List;

import org.sopt.soptlinkareer.domain.featuredrecruitment.dto.response.FeaturedRecruitmentResponse;
import org.sopt.soptlinkareer.domain.featuredrecruitment.exception.FeaturedRecruitmentSuccessCode;
import org.sopt.soptlinkareer.domain.featuredrecruitment.service.FeaturedRecruitmentService;
import org.sopt.soptlinkareer.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "FeaturedRecruitment", description = "홈 화면 추천 채용공고 API")
@RestController
@RequestMapping("/api/v1/featured-recruitments")
@RequiredArgsConstructor
public class FeaturedRecruitmentController {

  private final FeaturedRecruitmentService featuredRecruitmentService;

  @Operation(
      summary = "추천 채용공고 조회",
      description = "홈 화면에 고정 노출되는 추천 채용공고 목록을 반환합니다.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "추천 채용공고 조회 성공",
            content =
                @Content(schema = @Schema(implementation = FeaturedRecruitmentResponse.class)))
      })
  @GetMapping
  public ResponseEntity<BaseResponse<List<FeaturedRecruitmentResponse>>> getFeaturedRecruitments() {
    return ResponseEntity.ok(
        BaseResponse.onSuccess(
            FeaturedRecruitmentSuccessCode.GET_FEATURED_RECRUITMENTS_SUCCESS,
            featuredRecruitmentService.getFeaturedRecruitments()));
  }
}
