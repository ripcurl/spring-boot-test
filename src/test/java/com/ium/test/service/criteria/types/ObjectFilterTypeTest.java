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
public class ObjectFilterTypeTest {
  private static final Object value = new Object();

  @Mock Expression<? extends Comparable> property;
  @Mock CriteriaBuilder criteriaBuilderMock;

  private ObjectFilterType type = new ObjectFilterType();

  @Test(expected = IllegalArgumentException.class)
  public void testException() {
    SingularAttributePath property = Mockito.mock(SingularAttributePath.class, RETURNS_DEEP_STUBS);
    when(property.getAttribute().getName()).thenReturn("fieldName");
    type.createPredicate(property, value, Comparison.END_ILIKE, criteriaBuilderMock);
  }

  @Test
  public void testEquals() {
    type.createPredicate(property, value, Comparison.EQUALS, criteriaBuilderMock);
    verify(criteriaBuilderMock).equal(property, value);
  }

  @Test
  public void testNullComparison() {
    type.createPredicate(property, value, null, criteriaBuilderMock);
    verify(criteriaBuilderMock).equal(property, value);
  }

  @Test
  public void testEqualsNull() {
    type.createPredicate(property, null, Comparison.EQUALS, criteriaBuilderMock);
    verify(criteriaBuilderMock).isNull(property);
  }

  @Test
  public void testNotEquals() {
    type.createPredicate(property, value, Comparison.NOT_EQUALS, criteriaBuilderMock);
    verify(criteriaBuilderMock).notEqual(property, value);
  }

  @Test
  public void testNotEqualsNull() {
    type.createPredicate(property, null, Comparison.NOT_EQUALS, criteriaBuilderMock);
    verify(criteriaBuilderMock).isNotNull(property);
  }
}