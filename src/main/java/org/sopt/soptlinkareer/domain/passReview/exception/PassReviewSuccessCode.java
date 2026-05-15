package org.sopt.soptlinkareer.domain.passReview.exception;

import org.sopt.soptlinkareer.global.exception.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;

public enum PassReviewSuccessCode implements BaseSuccessCode {
  GET_PASS_REVIEWS_SUCCESS(HttpStatus.OK, "PASS_REVIEW2001", "합격 후기 전체 조회에 성공하였습니다.");

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  PassReviewSuccessCode(HttpStatus httpStatus, String code, String message) {
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
