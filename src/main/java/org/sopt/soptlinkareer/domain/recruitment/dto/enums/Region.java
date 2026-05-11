package org.sopt.soptlinkareer.domain.recruitment.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Region {
  SEOUL("서울"),
  BUSAN("부산"),
  DAEGU("대구"),
  INCHEON("인천"),
  GWANGJU("광주"),
  DAEJEON("대전"),
  ULSAN("울산"),
  GYEONGGI("경기"),
  GANGWON("강원"),
  CHUNGCHEONG("충청"),
  JEOLLA("전라"),
  GYEONGSANG("경상"),
  JEJU("제주"),
  SEJONG("세종"),
  OVERSEAS("해외");

  private final String label;

  Region(String label) {
    this.label = label;
  }

  @JsonValue
  public String getLabel() {
    return label;
  }

  @JsonCreator
  public static Region from(String value) {
    for (Region region : values()) {
      if (region.label.equals(value)) {
        return region;
      }
    }
    throw new IllegalArgumentException("Unknown Region: " + value);
  }
}
