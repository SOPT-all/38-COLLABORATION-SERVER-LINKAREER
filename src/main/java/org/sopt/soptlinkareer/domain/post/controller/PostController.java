package org.sopt.soptlinkareer.domain.post.controller;

import java.util.List;

import org.sopt.soptlinkareer.domain.post.dto.response.PostResponse;
import org.sopt.soptlinkareer.domain.post.exception.PostSuccessCode;
import org.sopt.soptlinkareer.domain.post.service.PostService;
import org.sopt.soptlinkareer.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@Tag(name = "Post", description = "게시글 API")
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
  private final PostService postService;

  @Operation(summary = "게시글 전체 조회", description = "등록된 게시글 전체 목록을 조회합니다.")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "게시글 전체 조회 성공")})
  @GetMapping
  public ResponseEntity<BaseResponse<List<PostResponse>>> getPosts() {
    List<PostResponse> response = postService.getPosts();
    return ResponseEntity.ok(BaseResponse.onSuccess(PostSuccessCode.GET_POSTS_SUCCESS, response));
  }
}
