package org.sopt.soptlinkareer.domain.recruitment.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.sopt.soptlinkareer.domain.recruitment.entity.Recruitment;
import org.sopt.soptlinkareer.domain.recruitment.entity.RecruitmentDeadlineType;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "채용공고 응답")
public record RecruitmentResponse(
    @Schema(description = "채용공고 ID", example = "1") Long id,
    @Schema(description = "회사명", example = "카카오") String company,
    @Schema(description = "공고 제목", example = "2026 카카오 신입 공채") String title,
    @Schema(description = "기업형태", example = "대기업") String companyType,
    @Schema(description = "채용형태", example = "신입, 경력직") String employmentType,
    @Schema(description = "직무", example = "IT/인터넷") String jobCategory,
    @Schema(description = "지역", example = "서울 강남구") String location,
    @Schema(description = "기업 로고 URL", example = "https://...") String imageUrl,
    @Schema(description = "모집 시작일", example = "2026-04-01") @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime recruitmentStartDate,
    @Schema(description = "모집 마감일", example = "2026-05-31") @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime recruitmentEndDate,
    @Schema(description = "마감 라벨 — 채용 시 마감이면 '채용 시 마감', 날짜 마감이면 '~MM.dd'", example = "~05.31")
        String deadlineLabel) {

  private static final DateTimeFormatter DEADLINE_FORMATTER = DateTimeFormatter.ofPattern("MM.dd");

  public static RecruitmentResponse from(Recruitment recruitment) {
    return new RecruitmentResponse(
        recruitment.getId(),
        recruitment.getCompany(),
        recruitment.getTitle(),
        recruitment.getCompanyType(),
        recruitment.getEmploymentType(),
        recruitment.getJobCategory(),
        recruitment.getLocation(),
        recruitment.getImageUrl(),
        recruitment.getRecruitmentStartDate(),
        recruitment.getRecruitmentEndDate(),
        formatDeadlineLabel(recruitment));
  }

  private static String formatDeadlineLabel(Recruitment recruitment) {
    if (recruitment.getRecruitmentDeadlineType() == RecruitmentDeadlineType.UNTIL_FILLED) {
      return "채용 시 마감";
    }
    return "~" + recruitment.getRecruitmentEndDate().format(DEADLINE_FORMATTER);
  }
}
