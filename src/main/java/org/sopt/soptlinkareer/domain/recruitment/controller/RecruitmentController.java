package org.sopt.soptlinkareer.domain.recruitment.controller;

import java.util.List;

import org.sopt.soptlinkareer.domain.recruitment.dto.enums.CompanyType;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.EmploymentType;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.JobCategory;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.Region;
import org.sopt.soptlinkareer.domain.recruitment.dto.request.RecruitmentSearchCondition;
import org.sopt.soptlinkareer.domain.recruitment.dto.response.RecruitmentDetailResponse;
import org.sopt.soptlinkareer.domain.recruitment.dto.response.RecruitmentResponse;
import org.sopt.soptlinkareer.domain.recruitment.exception.RecruitmentSuccessCode;
import org.sopt.soptlinkareer.domain.recruitment.service.RecruitmentService;
import org.sopt.soptlinkareer.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  @Operation(
      summary = "채용공고 상세 조회",
      description = "채용공고 ID로 공고의 상세 정보를 조회합니다. 담당업무, 자격요건, 우대사항은 리스트 형태로 반환됩니다.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "채용공고 상세 조회 성공"),
        @ApiResponse(responseCode = "404", description = "해당 공고를 찾을 수 없습니다.")
      })
  @GetMapping("/{recruitmentId}")
  public ResponseEntity<BaseResponse<RecruitmentDetailResponse>> getRecruitmentDetail(
      @Parameter(
              name = "recruitmentId",
              description = "조회할 채용공고 ID",
              required = true,
              example = "1",
              in = ParameterIn.PATH)
          @PathVariable
          Long recruitmentId) {
    RecruitmentDetailResponse response = recruitmentService.getRecruitmentDetail(recruitmentId);
    return ResponseEntity.ok(
        BaseResponse.onSuccess(RecruitmentSuccessCode.GET_RECRUITMENT_DETAIL, response));
  }
}
