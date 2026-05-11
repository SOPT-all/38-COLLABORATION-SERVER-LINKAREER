package org.sopt.soptlinkareer.domain.recruitment.controller;

import java.util.List;

import org.sopt.soptlinkareer.domain.recruitment.dto.enums.CompanyType;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.EmploymentType;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.JobCategory;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.Region;
import org.sopt.soptlinkareer.domain.recruitment.dto.request.RecruitmentSearchCondition;
import org.sopt.soptlinkareer.domain.recruitment.dto.response.RecruitmentResponse;
import org.sopt.soptlinkareer.domain.recruitment.exception.RecruitmentSuccessCode;
import org.sopt.soptlinkareer.domain.recruitment.service.RecruitmentService;
import org.sopt.soptlinkareer.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Recruitment", description = "채용공고 API")
@RestController
@RequestMapping("/api/v1/recruitments")
@RequiredArgsConstructor
public class RecruitmentController {

  private final RecruitmentService recruitmentService;

  @Operation(
      summary = "채용공고 목록 필터 조회",
      description =
          "다중 필터 조건으로 채용공고 목록을 조회합니다. 모든 필터는 선택값이며, 같은 필드 내 다중 선택은 OR, 다른 필드끼리는 AND로 동작합니다.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "채용공고 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = RecruitmentResponse.class)))
      })
  @GetMapping
  public ResponseEntity<BaseResponse<List<RecruitmentResponse>>> getRecruitments(
      @Parameter(
              description = "직무 필터 (다중 선택 가능, 한글 라벨로 전달)",
              array = @ArraySchema(schema = @Schema(implementation = JobCategory.class)))
          @RequestParam(required = false)
          List<JobCategory> jobCategories,
      @Parameter(
              description = "기업형태 필터 (다중 선택 가능, 한글 라벨로 전달)",
              array = @ArraySchema(schema = @Schema(implementation = CompanyType.class)))
          @RequestParam(required = false)
          List<CompanyType> companyTypes,
      @Parameter(
              description = "채용형태 필터 (다중 선택 가능, 한글 라벨로 전달)",
              array = @ArraySchema(schema = @Schema(implementation = EmploymentType.class)))
          @RequestParam(required = false)
          List<EmploymentType> employmentTypes,
      @Parameter(
              description = "지역 필터 (다중 선택 가능, 한글 라벨로 전달)",
              array = @ArraySchema(schema = @Schema(implementation = Region.class)))
          @RequestParam(required = false)
          List<Region> regions) {

    RecruitmentSearchCondition condition =
        new RecruitmentSearchCondition(jobCategories, companyTypes, employmentTypes, regions);

    return ResponseEntity.ok(
        BaseResponse.onSuccess(
            RecruitmentSuccessCode.GET_RECRUITMENTS_SUCCESS,
            recruitmentService.getRecruitments(condition)));
  }
}
