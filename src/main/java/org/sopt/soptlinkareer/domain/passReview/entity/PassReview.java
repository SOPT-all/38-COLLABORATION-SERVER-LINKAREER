package org.sopt.soptlinkareer.domain.passReview.entity;

import jakarta.persistence.*;

import org.sopt.soptlinkareer.global.entity.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pass_review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PassReview extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Builder
  private PassReview(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
