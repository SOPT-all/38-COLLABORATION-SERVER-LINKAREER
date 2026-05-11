package org.sopt.soptlinkareer.global.config;

import org.sopt.soptlinkareer.domain.recruitment.dto.enums.CompanyType;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.EmploymentType;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.JobCategory;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.Region;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EnumConverterConfig implements WebMvcConfigurer {

  // @JsonCreator는 JSON body에만 동작하므로
  // 쿼리 파라미터 바인딩을 위해 Converter를 별도 등록
  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(String.class, JobCategory.class, JobCategory::from);
    registry.addConverter(String.class, Region.class, Region::from);
    registry.addConverter(String.class, CompanyType.class, CompanyType::from);
    registry.addConverter(String.class, EmploymentType.class, EmploymentType::from);
  }
}
