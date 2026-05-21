package org.sopt.soptlinkareer.domain.featuredrecruitment.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.sopt.soptlinkareer.domain.featuredrecruitment.entity.FeaturedRecruitment;
import org.sopt.soptlinkareer.domain.recruitment.entity.Recruitment;
import org.sopt.soptlinkareer.domain.recruitment.entity.RecruitmentDeadlineType;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "홈 화면 추천 채용공고 응답")
public record FeaturedRecruitmentResponse(
    @Schema(description = "채용공고 ID", example = "1") Long id,
    @Schema(description = "기업 로고 URL", example = "https://...") String imageUrl,
    @Schema(description = "기업명", example = "카카오") String company,
    @Schema(description = "기업형태", example = "대기업") String companyType,
    @Schema(description = "공고 제목", example = "2026 카카오 신입 공채") String title,
    @Schema(description = "마감 D-day (상시채용이면 '상시채용', 아니면 'D-19')", example = "D-19") String dDay,
    @Schema(description = "모집 마감일", example = "2026-05-31") @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime recruitmentEndDate,
    @Schema(description = "모집 시작일", example = "2026-04-01") @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime recruitmentStartDate,
    @Schema(description = "직무", example = "IT/인터넷") String jobCategory) {

  public static FeaturedRecruitmentResponse from(FeaturedRecruitment featuredRecruitment) {
    Recruitment recruitment = featuredRecruitment.getRecruitment();
    return new FeaturedRecruitmentResponse(
        recruitment.getId(),
        recruitment.getImageUrl(),
        recruitment.getCompany(),
        recruitment.getCompanyType(),
        recruitment.getTitle(),
        formatDDay(recruitment),
        recruitment.getRecruitmentEndDate(),
        recruitment.getRecruitmentStartDate(),
        recruitment.getJobCategory());
  }

  private static String formatDDay(Recruitment recruitment) {
    if (recruitment.getRecruitmentDeadlineType() == RecruitmentDeadlineType.UNTIL_FILLED) {
      return "채용 시 마감";
    }
    long days =
        ChronoUnit.DAYS.between(LocalDate.now(), recruitment.getRecruitmentEndDate().toLocalDate());
    if (days > 0) return "D-" + days;
    if (days < 0) return "D+" + Math.abs(days);
    return "D-Day";
  }
}
