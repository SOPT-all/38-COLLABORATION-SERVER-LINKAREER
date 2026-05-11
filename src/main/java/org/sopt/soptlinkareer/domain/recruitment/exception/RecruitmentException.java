package org.sopt.soptlinkareer.domain.recruitment.exception;

import org.sopt.soptlinkareer.global.exception.CustomException;

public class RecruitmentException extends CustomException {

  public RecruitmentException(RecruitmentErrorCode errorCode) {
    super(errorCode);
  }
}
