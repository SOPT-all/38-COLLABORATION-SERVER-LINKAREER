package org.sopt.soptlinkareer.domain.passcoverletter.service;

import java.util.List;

import org.sopt.soptlinkareer.domain.passcoverletter.dto.response.PassCoverLetterResponse;
import org.sopt.soptlinkareer.domain.passcoverletter.repository.PassCoverLetterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PassCoverLetterService {

  private final PassCoverLetterRepository passCoverLetterRepository;

  public List<PassCoverLetterResponse> getAll() {
    return passCoverLetterRepository.findAll().stream().map(PassCoverLetterResponse::from).toList();
  }
}
