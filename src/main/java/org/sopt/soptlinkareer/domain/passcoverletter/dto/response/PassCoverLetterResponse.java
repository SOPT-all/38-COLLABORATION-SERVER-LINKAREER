package org.sopt.soptlinkareer.domain.passcoverletter.dto.response;

import org.sopt.soptlinkareer.domain.passcoverletter.entity.PassCoverLetter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "합격 자기소개서 응답")
public record PassCoverLetterResponse(
    @Schema(description = "합격 자기소개서 ID", example = "1") Long id,
    @Schema(description = "회사 이름", example = "카카오") String companyName,
    @Schema(description = "자기소개서 내용", example = "저는 항상 능동적으로...") String content) {

  public static PassCoverLetterResponse from(PassCoverLetter passCoverLetter) {
    return new PassCoverLetterResponse(
        passCoverLetter.getId(), passCoverLetter.getCompanyName(), passCoverLetter.getContent());
  }
}
