package org.sopt.soptlinkareer.domain.recruitment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.sopt.soptlinkareer.global.entity.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recruitment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String company;

  @Column(nullable = false)
  private String title;

  // 필터 필드는 모두 String으로 저장 (DB 유연성 확보)
  private String companyType;

  // "신입, 경력직" 처럼 복수값도 하나의 String으로 저장
  private String employmentType;

  private String jobCategory;

  private LocalDateTime recruitmentStartDate;

  private LocalDateTime recruitmentEndDate;

  // Entity 내부 상태이므로 Enum으로 관리
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RecruitmentDeadlineType recruitmentDeadlineType;

  private String imageUrl;

  private String location;

  @Column(columnDefinition = "TEXT")
  private String responsibilities;

  @Column(columnDefinition = "TEXT")
  private String qualifications;

  @Column(columnDefinition = "TEXT")
  private String preferences;

  @Builder
  private Recruitment(
      String company,
      String title,
      String companyType,
      String employmentType,
      String jobCategory,
      LocalDateTime recruitmentStartDate,
      LocalDateTime recruitmentEndDate,
      RecruitmentDeadlineType recruitmentDeadlineType,
      String imageUrl,
      String location,
      String responsibilities,
      String qualifications,
      String preferences) {
    this.company = company;
    this.title = title;
    this.companyType = companyType;
    this.employmentType = employmentType;
    this.jobCategory = jobCategory;
    this.recruitmentStartDate = recruitmentStartDate;
    this.recruitmentEndDate = recruitmentEndDate;
    this.recruitmentDeadlineType = recruitmentDeadlineType;
    this.imageUrl = imageUrl;
    this.location = location;
    this.responsibilities = responsibilities;
    this.qualifications = qualifications;
    this.preferences = preferences;
  }
}
