package com.ium.test.service.criteria.types;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.ium.test.rest.wsobjects.Comparison;
import com.ium.test.service.criteria.CriteriaFilterType;

/** <b>Description:</b> FilterType for objects. */
class ObjectFilterType extends CriteriaFilterType<Object> {

  @Override
  public Predicate createPredicate(Expression<? extends Comparable> property, Object value,
                                   Comparison comparison, CriteriaBuilder cb) {

    if (isEqual(comparison)) {
      return value == null ? cb.isNull(property) : cb.equal(property, value);
    }

    if (comparison == Comparison.NOT_EQUALS) {
      return value == null ? cb.isNotNull(property) : cb.notEqual(property, value);
    }

    throw newInvalidComparisonException(comparison, property);
  }
}
