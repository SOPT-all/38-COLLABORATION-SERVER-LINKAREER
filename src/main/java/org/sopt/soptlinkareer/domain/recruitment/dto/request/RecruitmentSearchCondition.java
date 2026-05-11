package org.sopt.soptlinkareer.domain.recruitment.dto.request;

import java.util.List;

import org.sopt.soptlinkareer.domain.recruitment.dto.enums.CompanyType;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.EmploymentType;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.JobCategory;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.Region;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "채용공고 필터 검색 조건 (모든 필드 선택값)")
public record RecruitmentSearchCondition(
    @Schema(description = "직무 필터 (다중 선택 가능)") List<JobCategory> jobCategories,
    @Schema(description = "기업형태 필터 (다중 선택 가능)") List<CompanyType> companyTypes,
    @Schema(description = "채용형태 필터 (다중 선택 가능)") List<EmploymentType> employmentTypes,
    @Schema(description = "지역 필터 (다중 선택 가능)") List<Region> regions) {}
