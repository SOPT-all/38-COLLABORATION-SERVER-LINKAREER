package org.sopt.soptlinkareer.domain.recruitment.repository;

import java.util.List;

import org.sopt.soptlinkareer.domain.recruitment.dto.enums.CompanyType;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.EmploymentType;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.JobCategory;
import org.sopt.soptlinkareer.domain.recruitment.dto.enums.Region;
import org.sopt.soptlinkareer.domain.recruitment.dto.request.RecruitmentSearchCondition;
import org.sopt.soptlinkareer.domain.recruitment.entity.QRecruitment;
import org.sopt.soptlinkareer.domain.recruitment.entity.Recruitment;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RecruitmentRepositoryImpl implements RecruitmentRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public List<Recruitment> findByCondition(RecruitmentSearchCondition condition) {
    QRecruitment recruitment = QRecruitment.recruitment;

    return queryFactory
        .selectFrom(recruitment)
        .where(
            jobCategoryIn(recruitment, condition.jobCategories()),
            companyTypeIn(recruitment, condition.companyTypes()),
            employmentTypeContains(recruitment, condition.employmentTypes()),
            regionStartsWith(recruitment, condition.regions()))
        .orderBy(recruitment.recruitmentStartDate.desc())
        .fetch();
  }

  // 직무: label 정확 일치 IN — null이면 QueryDSL이 조건 무시
  private BooleanExpression jobCategoryIn(QRecruitment recruitment, List<JobCategory> categories) {
    if (categories == null || categories.isEmpty()) return null;
    List<String> labels = categories.stream().map(JobCategory::getLabel).toList();
    return recruitment.jobCategory.in(labels);
  }

  // 기업형태: label 정확 일치 IN
  private BooleanExpression companyTypeIn(QRecruitment recruitment, List<CompanyType> types) {
    if (types == null || types.isEmpty()) return null;
    List<String> labels = types.stream().map(CompanyType::getLabel).toList();
    return recruitment.companyType.in(labels);
  }

  // 채용형태: "신입, 경력직" 같이 복수값이 저장되므로 contains OR로 묶음
  private BooleanExpression employmentTypeContains(
      QRecruitment recruitment, List<EmploymentType> types) {
    if (types == null || types.isEmpty()) return null;
    BooleanExpression result = null;
    for (EmploymentType type : types) {
      BooleanExpression expr = recruitment.employmentType.contains(type.getLabel());
      result = (result == null) ? expr : result.or(expr);
    }
    return result;
  }

  // 지역: "서울" 선택 시 "서울 강남구", "서울 마포구" 모두 포함 — startsWith OR로 묶음
  private BooleanExpression regionStartsWith(QRecruitment recruitment, List<Region> regions) {
    if (regions == null || regions.isEmpty()) return null;
    BooleanExpression result = null;
    for (Region region : regions) {
      BooleanExpression expr = recruitment.location.startsWith(region.getLabel());
      result = (result == null) ? expr : result.or(expr);
    }
    return result;
  }
}
