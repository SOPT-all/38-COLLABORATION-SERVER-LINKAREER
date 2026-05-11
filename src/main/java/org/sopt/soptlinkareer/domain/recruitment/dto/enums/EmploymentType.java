package org.sopt.soptlinkareer.domain.recruitment.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EmploymentType {
  NEW_HIRE("신입"),
  EXPERIENCED("경력직"),
  INTERN("인턴"),
  CONTRACT("계약직");

  private final String label;

  EmploymentType(String label) {
    this.label = label;
  }

  @JsonValue
  public String getLabel() {
    return label;
  }

  @JsonCreator
  public static EmploymentType from(String value) {
    for (EmploymentType type : values()) {
      if (type.label.equals(value)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unknown EmploymentType: " + value);
  }
}
