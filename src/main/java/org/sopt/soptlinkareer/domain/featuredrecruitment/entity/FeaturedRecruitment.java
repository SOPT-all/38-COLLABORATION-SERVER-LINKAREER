package org.sopt.soptlinkareer.domain.featuredrecruitment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.sopt.soptlinkareer.domain.recruitment.entity.Recruitment;
import org.sopt.soptlinkareer.global.entity.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "featured_recruitment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeaturedRecruitment extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "recruitment_id", nullable = false)
  private Recruitment recruitment;
}
