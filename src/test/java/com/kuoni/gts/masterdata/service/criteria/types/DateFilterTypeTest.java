package com.kuoni.gts.masterdata.service.criteria.types;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;

import org.hibernate.jpa.criteria.path.SingularAttributePath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.kuoni.services.domain.v1_0.Comparison;

@RunWith(MockitoJUnitRunner.class)
public class DateFilterTypeTest {

  @Mock Expression<? extends Date> property;
  @Mock CriteriaBuilder criteriaBuilderMock;

  private DateFilterType type = new DateFilterType();
  private Date dateValue = new Date();

  @Test
  public void testNoComparisonEquals() {
    type.createPredicate(property, dateValue, null, criteriaBuilderMock);
    verify(criteriaBuilderMock).equal(property, dateValue);
  }

  @Test
  public void testEquals() {
    type.createPredicate(property, dateValue, Comparison.EQUALS, criteriaBuilderMock);
    verify(criteriaBuilderMock).equal(property, dateValue);
  }

  @Test
  public void testNotEquals() {
    type.createPredicate(property, dateValue, Comparison.NOT_EQUALS, criteriaBuilderMock);
    verify(criteriaBuilderMock).notEqual(property, dateValue);
  }

  @Test
  public void testLesserThan() {
    type.createPredicate(property, dateValue, Comparison.LESSER_THAN, criteriaBuilderMock);
    verify(criteriaBuilderMock).lessThan(property, dateValue);
  }

  @Test
  public void testLesserThanOrEquals() {
    type.createPredicate(property, dateValue, Comparison.LESSER_THAN_OR_EQUALS, criteriaBuilderMock);
    verify(criteriaBuilderMock).lessThanOrEqualTo(property, dateValue);
  }

  @Test
  public void testGreaterThanOrEquals() {
    type.createPredicate(property, dateValue, Comparison.GREATER_THAN_OR_EQUALS, criteriaBuilderMock);
    verify(criteriaBuilderMock).greaterThanOrEqualTo(property, dateValue);
  }

  @Test
  public void testGreaterThan() {
    type.createPredicate(property, dateValue, Comparison.GREATER_THAN, criteriaBuilderMock);
    verify(criteriaBuilderMock).greaterThan(property, dateValue);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidComparison() {
    SingularAttributePath property = Mockito.mock(SingularAttributePath.class, RETURNS_DEEP_STUBS);
    when(property.getAttribute().getName()).thenReturn("fieldName");
    type.createPredicate(property, dateValue, Comparison.ILIKE, criteriaBuilderMock);
  }

}