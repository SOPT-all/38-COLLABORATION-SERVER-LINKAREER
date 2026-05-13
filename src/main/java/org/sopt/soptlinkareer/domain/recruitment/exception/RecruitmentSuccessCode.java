package org.sopt.soptlinkareer.domain.recruitment.exception;

import org.sopt.soptlinkareer.global.exception.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;

public enum RecruitmentSuccessCode implements BaseSuccessCode {
  GET_RECRUITMENTS_SUCCESS(HttpStatus.OK, "RECRUITMENT2001", "채용공고 목록 조회에 성공했습니다."),
  GET_RECRUITMENT_DETAIL(HttpStatus.OK, "RECRUITMENT_2002", "채용공고 상세 조회 성공");

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  RecruitmentSuccessCode(HttpStatus httpStatus, String code, String message) {
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
