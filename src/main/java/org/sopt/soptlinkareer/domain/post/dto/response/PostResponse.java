package org.sopt.soptlinkareer.domain.post.dto.response;

import java.time.LocalDate;

import org.sopt.soptlinkareer.domain.post.entity.Post;

import io.swagger.v3.oas.annotations.media.Schema;

public record PostResponse(
    @Schema(description = "게시글 ID") Long id,
    @Schema(description = "게시글 제목") String title,
    @Schema(description = "게시글 이미지 URL") String imageUrl,
    @Schema(description = "공지 게시글 여부") boolean isNotice,
    @Schema(description = "기업 - null 가능") String company,
    @Schema(description = "직무 - null 가능") String job,
    @Schema(description = "게시자 아이디") String authorId,
    @Schema(description = "게시 날짜") LocalDate postDate,
    @Schema(description = "조회수") int viewCount,
    @Schema(description = "추천수") int likeCount,
    @Schema(description = "스크랩수") int scrapCount,
    @Schema(description = "댓글수") int commentCount) {

  public static PostResponse from(Post post) {
    return new PostResponse(
        post.getId(),
        post.getTitle(),
        post.getImageUrl(),
        post.isNotice(),
        post.getCompany(),
        post.getJob(),
        post.getAuthorId(),
        post.getPostDate(),
        post.getViewCount(),
        post.getLikeCount(),
        post.getScrapCount(),
        post.getCommentCount());
  }
}
