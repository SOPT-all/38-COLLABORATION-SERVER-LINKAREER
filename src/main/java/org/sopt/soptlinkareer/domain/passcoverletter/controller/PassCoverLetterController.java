package org.sopt.soptlinkareer.domain.passcoverletter.controller;

import java.util.List;

import org.sopt.soptlinkareer.domain.passcoverletter.dto.response.PassCoverLetterResponse;
import org.sopt.soptlinkareer.domain.passcoverletter.exception.code.PassCoverLetterSuccessCode;
import org.sopt.soptlinkareer.domain.passcoverletter.service.PassCoverLetterService;
import org.sopt.soptlinkareer.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "PassCoverLetter", description = "합격 자기소개서 API")
@RestController
@RequestMapping("/api/v1/pass-cover-letters")
@RequiredArgsConstructor
public class PassCoverLetterController {

  private final PassCoverLetterService passCoverLetterService;

  @Operation(summary = "합격 자기소개서 전체 조회", description = "저장된 모든 합격 자기소개서 목록을 반환합니다.")
  @GetMapping
  public ResponseEntity<BaseResponse<List<PassCoverLetterResponse>>> getAll() {
    return ResponseEntity.ok(
        BaseResponse.onSuccess(
            PassCoverLetterSuccessCode.GET_ALL_PASS_COVER_LETTERS_SUCCESS,
            passCoverLetterService.getAll()));
  }
}
