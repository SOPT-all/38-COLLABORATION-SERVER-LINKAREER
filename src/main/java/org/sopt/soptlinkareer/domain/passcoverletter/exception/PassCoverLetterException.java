package org.sopt.soptlinkareer.domain.passcoverletter.exception;

import org.sopt.soptlinkareer.domain.passcoverletter.exception.code.PassCoverLetterErrorCode;
import org.sopt.soptlinkareer.global.exception.CustomException;

public class PassCoverLetterException extends CustomException {

  public PassCoverLetterException(PassCoverLetterErrorCode errorCode) {
    super(errorCode);
  }
}
