package org.sopt.soptlinkareer.domain.recruitment.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CompanyType {
  LARGE("대기업"),
  SMALL("중소기업"),
  PUBLIC("공공기관/공기업"),
  FOREIGN("외국계기업"),
  MID("중견기업"),
  NONPROFIT("비영리단체/협회/재단"),
  STARTUP("스타트업"),
  FINANCIAL("금융권"),
  HOSPITAL("병원"),
  ETC("기타");

  private final String label;

  CompanyType(String label) {
    this.label = label;
  }

  @JsonValue
  public String getLabel() {
    return label;
  }

  @JsonCreator
  public static CompanyType from(String value) {
    for (CompanyType type : values()) {
      if (type.label.equals(value)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unknown CompanyType: " + value);
  }
}
