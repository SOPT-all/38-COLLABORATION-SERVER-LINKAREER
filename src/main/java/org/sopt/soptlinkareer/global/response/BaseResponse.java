package org.sopt.soptlinkareer.global.response;

import org.sopt.soptlinkareer.global.exception.code.BaseErrorCode;
import org.sopt.soptlinkareer.global.exception.code.BaseSuccessCode;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonPropertyOrder({"status", "code", "message", "data"})
public class BaseResponse<T> {

  @Schema(description = "HTTP 상태 코드", example = "200")
  private final int status;

  @Schema(description = "응답 상태 코드")
  private final String code;

  @Schema(description = "응답 메시지")
  private final String message;

  @Schema(description = "응답 결과 (실패 시 null)")
  private T data;

  public BaseResponse(int status, String code, String message, T data) {
    this.status = status;
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static <T> BaseResponse<T> onSuccess(BaseSuccessCode successCode, T data) {
    return new BaseResponse<>(
        successCode.getHttpStatus().value(), successCode.getCode(), successCode.getMessage(), data);
  }

  public static <T> BaseResponse<T> onFailure(BaseErrorCode errorCode) {
    return new BaseResponse<>(
        errorCode.getHttpStatus().value(), errorCode.getCode(), errorCode.getMessage(), null);
  }

  public int getStatus() {
    return status;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }
}
