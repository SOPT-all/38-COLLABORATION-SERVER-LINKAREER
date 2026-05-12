package org.sopt.soptlinkareer.domain.recruitment.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum JobCategory {
  MARKETING("마케팅/광고/홍보"),
  SALES("영업/고객상담"),
  OFFICE("경영/사무"),
  PRODUCTION("생산/제조"),
  RND("연구개발/설계"),
  IT("IT/인터넷"),
  SERVICE("서비스"),
  TRADE("무역/유통"),
  MEDICAL("의료"),
  CONSTRUCTION("건설"),
  EDUCATION("교육"),
  DESIGN("디자인"),
  PROFESSIONAL("전문/특수직"),
  MEDIA("미디어"),
  ETC("기타");

  private final String label;

  JobCategory(String label) {
    this.label = label;
  }

  @JsonValue
  public String getLabel() {
    return label;
  }

  // 요청 역직렬화 시 한글 label로 매핑
  @JsonCreator
  public static JobCategory from(String value) {
    for (JobCategory category : values()) {
      if (category.label.equals(value)) {
        return category;
      }
    }
    throw new IllegalArgumentException("Unknown JobCategory: " + value);
  }
}
