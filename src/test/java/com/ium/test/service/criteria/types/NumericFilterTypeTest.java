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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NumericFilterTypeTest {
  private static final int value = 123;

  @Mock Expression<? extends Comparable> property;
  @Mock CriteriaBuilder criteriaBuilderMock;

  private NumericFilterType type = new NumericFilterType();

  @Test(expected = IllegalArgumentException.class)
  public void testException() {
    SingularAttributePath property = Mockito.mock(SingularAttributePath.class, RETURNS_DEEP_STUBS);
    when(property.getAttribute().getName()).thenReturn("fieldName");
    type.createPredicate(property, value, Comparison.END_ILIKE, criteriaBuilderMock);
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
  public void testLesserThan() {
    type.createPredicate(property, value, Comparison.LESSER_THAN, criteriaBuilderMock);
    verify(criteriaBuilderMock, times(1)).lt((Expression<? extends Number>) property, value);
  }

  @Test
  public void testLesserThanOrEquals() {
    type.createPredicate(property, value, Comparison.LESSER_THAN_OR_EQUALS, criteriaBuilderMock);
    verify(criteriaBuilderMock, times(1)).le((Expression<? extends Number>) property, value);
  }

  @Test
  public void testGreaterThanOrEquals() {
    type.createPredicate(property, value, Comparison.GREATER_THAN_OR_EQUALS, criteriaBuilderMock);
    verify(criteriaBuilderMock, times(1)).ge((Expression<? extends Number>) property, value);
  }

  @Test
  public void testGreaterThan() {
    type.createPredicate(property, value, Comparison.GREATER_THAN, criteriaBuilderMock);
    verify(criteriaBuilderMock, times(1)).gt((Expression<? extends Number>) property, value);
  }
}