package org.sopt.soptlinkareer.domain.passcoverletter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.sopt.soptlinkareer.global.entity.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pass_cover_letter")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PassCoverLetter extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String companyName;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;
}
