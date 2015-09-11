package com.ium.test.service.criteria.types;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.ium.test.rest.wsobjects.Comparison;
import com.ium.test.service.criteria.CriteriaFilterType;

/**
 * <b>Description:</b> FilterType for numbers.
 */
class NumericFilterType extends CriteriaFilterType<Number> {

  @Override
  public Predicate createPredicate(Expression<? extends Comparable> property, Number value,
                                   Comparison comparison, CriteriaBuilder cb) {
    Expression<? extends Number> numberProperty = (Expression<? extends Number>) property;

    if (isEqual(comparison)) {
      return cb.equal(numberProperty, value);
    }

    if (Comparison.NOT_EQUALS == comparison) {
      return cb.notEqual(numberProperty, value);
    }

    if (comparison == Comparison.LESSER_THAN_OR_EQUALS) {
      return cb.le(numberProperty, value);
    }

    if (comparison == Comparison.GREATER_THAN_OR_EQUALS) {
      return cb.ge(numberProperty, value);
    }

    if (comparison == Comparison.LESSER_THAN) {
      return cb.lt(numberProperty, value);
    }

    if (comparison == Comparison.GREATER_THAN) {
      return cb.gt(numberProperty, value);
    }

    throw newInvalidComparisonException(comparison, property);
  }
}
