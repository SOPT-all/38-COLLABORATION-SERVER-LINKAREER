package org.sopt.soptlinkareer.global.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolationException;

import org.sopt.soptlinkareer.global.exception.code.BaseErrorCode;
import org.sopt.soptlinkareer.global.exception.code.GlobalErrorCode;
import org.sopt.soptlinkareer.global.response.BaseResponse;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<BaseResponse<Void>> handleCustomException(CustomException e) {
    BaseErrorCode errorCode = e.getErrorCode();
    return ResponseEntity.status(errorCode.getHttpStatus()).body(BaseResponse.onFailure(errorCode));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseResponse<Void>> handleException(Exception e) {
    return ResponseEntity.status(500)
        .body(BaseResponse.onFailure(GlobalErrorCode.COMMON_INTERNAL_SERVER_ERROR));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<BaseResponse<Void>> handleConstraintViolationException(
      ConstraintViolationException e) {
    String message = e.getConstraintViolations().iterator().next().getMessage();
    BaseErrorCode errorCode = findErrorCodeByMessage(message);

    if (errorCode != null) {
      return ResponseEntity.status(errorCode.getHttpStatus())
          .body(BaseResponse.onFailure(errorCode));
    }
    return ResponseEntity.status(400)
        .body(BaseResponse.onFailure(GlobalErrorCode.COMMON_BAD_REQUEST));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<BaseResponse<Void>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    BaseErrorCode errorCode = findErrorCodeByMessage(defaultMessage);

    if (errorCode != null) {
      return ResponseEntity.status(errorCode.getHttpStatus())
          .body(BaseResponse.onFailure(errorCode));
    }
    return ResponseEntity.status(400)
        .body(BaseResponse.onFailure(GlobalErrorCode.COMMON_BAD_REQUEST));
  }

  private final Map<String, BaseErrorCode> errorCodeMap = new HashMap<>();

  @PostConstruct
  private void initErrorCodeMap() {
    ClassPathScanningCandidateComponentProvider scanner =
        new ClassPathScanningCandidateComponentProvider(false);
    scanner.addIncludeFilter(new AssignableTypeFilter(BaseErrorCode.class));

    Set<BeanDefinition> candidates = scanner.findCandidateComponents("org.sopt.soptlinkareer");
    for (BeanDefinition bd : candidates) {
      try {
        Class<?> clazz = Class.forName(bd.getBeanClassName());
        if (clazz.isEnum()) {
          for (Object constant : clazz.getEnumConstants()) {
            BaseErrorCode code = (BaseErrorCode) constant;
            errorCodeMap.put(code.getMessage(), code);
          }
        }
      } catch (ClassNotFoundException ignored) {
      }
    }
  }

  private BaseErrorCode findErrorCodeByMessage(String message) {
    return errorCodeMap.get(message);
  }
}
