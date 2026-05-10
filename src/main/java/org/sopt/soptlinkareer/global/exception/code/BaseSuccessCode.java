package org.sopt.soptlinkareer.global.exception.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
  HttpStatus getHttpStatus();

  String getCode();

  String getMessage();
}
