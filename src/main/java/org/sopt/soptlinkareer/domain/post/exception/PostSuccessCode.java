package org.sopt.soptlinkareer.domain.post.exception;

import org.sopt.soptlinkareer.global.exception.code.BaseSuccessCode;
import org.springframework.http.HttpStatus;

public enum PostSuccessCode implements BaseSuccessCode {
  GET_POSTS_SUCCESS(HttpStatus.OK, "POST_2001", "게시글 전체 조회 성공");

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  PostSuccessCode(HttpStatus httpStatus, String code, String message) {
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
