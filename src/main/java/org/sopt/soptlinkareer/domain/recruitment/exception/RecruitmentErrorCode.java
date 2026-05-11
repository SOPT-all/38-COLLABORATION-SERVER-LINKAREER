package org.sopt.soptlinkareer.domain.recruitment.exception;

import org.sopt.soptlinkareer.global.exception.code.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum RecruitmentErrorCode implements BaseErrorCode {
  RECRUITMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "RECRUITMENT4001", "채용공고를 찾을 수 없습니다.");

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  RecruitmentErrorCode(HttpStatus httpStatus, String code, String message) {
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
