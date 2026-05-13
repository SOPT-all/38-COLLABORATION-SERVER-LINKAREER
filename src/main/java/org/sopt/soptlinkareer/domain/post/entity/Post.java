package org.sopt.soptlinkareer.domain.post.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import org.sopt.soptlinkareer.global.entity.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class Post extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  private String imageUrl;

  @Column(nullable = false)
  private boolean isNotice;

  private String company;

  private String job;

  @Column(nullable = false)
  private String authorId;

  @Column(nullable = false)
  private LocalDate postDate;

  @Column(nullable = false)
  private int viewCount;

  @Column(nullable = false)
  private int likeCount;

  @Column(nullable = false)
  private int scrapCount;

  @Column(nullable = false)
  private int commentCount;

  public static Post of(
      String title,
      String imageUrl,
      boolean isNotice,
      String company,
      String job,
      String authorId,
      LocalDate postDate) {
    Post post = new Post();
    post.title = title;
    post.imageUrl = imageUrl;
    post.isNotice = isNotice;
    post.company = company;
    post.job = job;
    post.authorId = authorId;
    post.postDate = postDate;
    post.viewCount = 0;
    post.likeCount = 0;
    post.scrapCount = 0;
    post.commentCount = 0;
    return post;
  }
}
