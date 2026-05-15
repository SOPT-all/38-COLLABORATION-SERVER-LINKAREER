package org.sopt.soptlinkareer.domain.recruitment.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.sopt.soptlinkareer.domain.recruitment.entity.Recruitment;
import org.sopt.soptlinkareer.domain.recruitment.entity.RecruitmentDeadlineType;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

public record RecruitmentDetailResponse(
    @Schema(description = "공고 ID") Long id,
    @Schema(description = "회사명") String company,
    @Schema(description = "공고 제목") String title,
    @Schema(description = "기업 형태", example = "중소기업") String companyType,
    @Schema(description = "채용 형태", example = "체험형 인턴") String employmentType,
    @Schema(description = "직무 카테고리", example = "마케팅·광고·홍보 전체") String jobCategory,
    @Schema(description = "근무 지역", example = "서울 강남구") String location,
    @Schema(description = "모집 시작일", example = "2026-04-01") @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime recruitmentStartDate,
    @Schema(description = "모집 마감일", example = "2026-05-31") @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime recruitmentEndDate,
    @Schema(description = "마감일 표시값 - FIXED_DATE면 날짜(MM.dd), UNTIL_FILLED면 '채용 시 마감'")
        String deadlineLabel,
    @Schema(description = "마감 유형") RecruitmentDeadlineType recruitmentDeadlineType,
    @Schema(description = "모집 기간 표시값", example = "04.23 ~ 05.31 / 04.23 ~ 채용 시 마감")
        String recruitmentPeriod,
    @Schema(description = "담당업무") List<String> responsibilities,
    @Schema(description = "자격요건") List<String> qualifications,
    @Schema(description = "우대사항") List<String> preferences) {
  private static final DateTimeFormatter DEADLINE_FORMATTER = DateTimeFormatter.ofPattern("MM.dd");

  public static RecruitmentDetailResponse from(Recruitment recruitment) {
    return new RecruitmentDetailResponse(
        recruitment.getId(),
        recruitment.getCompany(),
        recruitment.getTitle(),
        recruitment.getCompanyType(),
        recruitment.getEmploymentType(),
        recruitment.getJobCategory(),
        recruitment.getLocation(),
        recruitment.getRecruitmentStartDate(),
        recruitment.getRecruitmentEndDate(),
        formatDeadlineLabel(recruitment),
        recruitment.getRecruitmentDeadlineType(),
        formatRecruitmentPeriod(recruitment),
        splitLines(recruitment.getResponsibilities()),
        splitLines(recruitment.getQualifications()),
        splitLines(recruitment.getPreferences()));
  }

  private static String formatRecruitmentPeriod(Recruitment recruitment) {
    String start =
        recruitment.getRecruitmentStartDate() != null
            ? recruitment.getRecruitmentStartDate().format(DEADLINE_FORMATTER)
            : "";

    String end =
        recruitment.getRecruitmentDeadlineType() == RecruitmentDeadlineType.UNTIL_FILLED
            ? "채용 시 마감"
            : recruitment.getRecruitmentEndDate() != null
                ? recruitment.getRecruitmentEndDate().format(DEADLINE_FORMATTER)
                : "";

    return start + " ~ " + end;
  }

  private static String formatDeadlineLabel(Recruitment recruitment) {
    if (recruitment.getRecruitmentDeadlineType() == RecruitmentDeadlineType.UNTIL_FILLED) {
      return "채용 시 마감";
    }
    return recruitment.getRecruitmentEndDate().format(DEADLINE_FORMATTER);
  }

  private static List<String> splitLines(String text) {
    if (text == null || text.isBlank()) return List.of();
    return List.of(text.split("\n"));
  }
}
