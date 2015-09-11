package com.ium.test.service.criteria.types;

import com.ium.test.rest.wsobjects.Comparison;
import org.hibernate.jpa.criteria.path.SingularAttributePath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StringFilterTypeTest {
  private static final String value = "123";

  @Mock Expression<? extends Comparable> property;
  @Mock CriteriaBuilder criteriaBuilderMock;

  private StringFilterType type = new StringFilterType();

  @Test(expected = IllegalArgumentException.class)
  public void testException() {
    SingularAttributePath property = Mockito.mock(SingularAttributePath.class, RETURNS_DEEP_STUBS);
    when(property.getAttribute().getName()).thenReturn("fieldName");
    type.createPredicate(property, value, Comparison.GREATER_THAN, criteriaBuilderMock);
  }

  @Test
  public void testEquals() {
    type.createPredicate(property, value, Comparison.EQUALS, criteriaBuilderMock);
    verify(criteriaBuilderMock, times(1)).equal(property, value);
  }

  @Test
  public void testNotEquals() {
    type.createPredicate(property, value, Comparison.NOT_EQUALS, criteriaBuilderMock);
    verify(criteriaBuilderMock, times(1)).notEqual(property, value);
  }

  @Test
  public void testLike() {
    type.createPredicate(property, value, Comparison.LIKE, criteriaBuilderMock);
    verify(criteriaBuilderMock, times(1)).like((Expression<String>) property, "%" + value + "%");
  }

  @Test
  public void testILike() {
    type.createPredicate(property, value, Comparison.ILIKE, criteriaBuilderMock);
    verify(criteriaBuilderMock, times(1)).like(any(Expression.class), anyString());
  }

  @Test
  public void testStartLike() {
    type.createPredicate(property, value, Comparison.START_LIKE, criteriaBuilderMock);
    verify(criteriaBuilderMock, times(1)).like(any(Expression.class), anyString());
  }

  @Test
  public void testEndLike() {
    type.createPredicate(property, value, Comparison.END_LIKE, criteriaBuilderMock);
    verify(criteriaBuilderMock, times(1)).like(any(Expression.class), anyString());
  }

  @Test
  public void testStartILike() {
    type.createPredicate(property, value, Comparison.START_ILIKE, criteriaBuilderMock);
    verify(criteriaBuilderMock, times(1)).like(any(Expression.class), anyString());
  }

  @Test
  public void testEndILike() {
    type.createPredicate(property, value, Comparison.END_ILIKE, criteriaBuilderMock);
    verify(criteriaBuilderMock, times(1)).like(any(Expression.class), anyString());
  }
}