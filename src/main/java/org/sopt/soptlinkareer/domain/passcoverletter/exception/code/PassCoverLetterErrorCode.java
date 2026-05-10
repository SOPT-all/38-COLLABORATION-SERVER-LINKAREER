package org.sopt.soptlinkareer.domain.passcoverletter.exception.code;

import org.sopt.soptlinkareer.global.exception.code.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum PassCoverLetterErrorCode implements BaseErrorCode {
  PASS_COVER_LETTER_NOT_FOUND(
      HttpStatus.NOT_FOUND, "PASS_COVER_LETTER4001", "합격 자기소개서를 찾을 수 없습니다.");

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  PassCoverLetterErrorCode(HttpStatus httpStatus, String code, String message) {
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
