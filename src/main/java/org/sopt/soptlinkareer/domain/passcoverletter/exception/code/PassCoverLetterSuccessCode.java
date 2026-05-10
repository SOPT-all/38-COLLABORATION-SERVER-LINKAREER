package org.sopt.soptlinkareer.domain.passcoverletter.exception.code;

import org.sopt.soptlinkareer.global.exception.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;

public enum PassCoverLetterSuccessCode implements BaseSuccessCode {
  GET_ALL_PASS_COVER_LETTERS_SUCCESS(
      HttpStatus.OK, "PASS_COVER_LETTER2001", "합격 자기소개서 전체 조회에 성공했습니다.");

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  PassCoverLetterSuccessCode(HttpStatus httpStatus, String code, String message) {
    this.httpStatus = httpStatus;
    this.code = code;
    this.message = message;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
