package org.sopt.soptlinkareer.domain.featuredrecruitment.exception;

import org.sopt.soptlinkareer.global.exception.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;

public enum FeaturedRecruitmentSuccessCode implements BaseSuccessCode {
  GET_FEATURED_RECRUITMENTS_SUCCESS(
      HttpStatus.OK, "FEATURED_RECRUITMENT2001", "추천 채용공고 조회에 성공했습니다.");

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  FeaturedRecruitmentSuccessCode(HttpStatus httpStatus, String code, String message) {
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
