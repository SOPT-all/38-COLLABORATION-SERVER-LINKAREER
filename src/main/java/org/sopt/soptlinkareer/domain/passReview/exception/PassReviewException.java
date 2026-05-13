package org.sopt.soptlinkareer.domain.passReview.exception;

import org.sopt.soptlinkareer.domain.recruitment.exception.RecruitmentErrorCode;
import org.sopt.soptlinkareer.global.exception.CustomException;

public class PassReviewException extends CustomException {
  public PassReviewException(RecruitmentErrorCode errorCode) {
    super(errorCode);
  }
}
