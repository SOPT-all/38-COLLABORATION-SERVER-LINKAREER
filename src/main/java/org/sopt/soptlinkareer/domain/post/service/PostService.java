package org.sopt.soptlinkareer.domain.post.service;

import java.util.List;

import org.sopt.soptlinkareer.domain.post.dto.response.PostResponse;
import org.sopt.soptlinkareer.domain.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
  private final PostRepository postRepository;

  public List<PostResponse> getPosts() {
    return postRepository.findAll().stream().map(PostResponse::from).toList();
  }
}
