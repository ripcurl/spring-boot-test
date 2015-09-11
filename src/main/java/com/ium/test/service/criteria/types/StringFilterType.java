package com.ium.test.service.criteria.types;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.ium.test.rest.wsobjects.Comparison;
import com.ium.test.service.criteria.CriteriaFilterType;

/**
 * FilterType for strings. Supports below comparisons:
 * EQUALS; NOT_EQUALS;
 * LIKE; ILIKE;
 * START_LIKE; END_LIKE;
 * START_ILIKE; END_ILIKE;
 */
class StringFilterType extends CriteriaFilterType<String> {

  public Predicate createPredicate(Expression<? extends Comparable> property, String value,
                                   Comparison comparison, CriteriaBuilder cb) {

    Expression<String> stringProperty = (Expression<String>) property;

    if (isEqual(comparison)) {
      return cb.equal(stringProperty, value);
    }

    if (Comparison.NOT_EQUALS == comparison) {
      return cb.notEqual(stringProperty, value);
    }

    if (Comparison.LIKE == comparison) {
      return cb.like(stringProperty, "%" + value + "%");
    }

    if (Comparison.ILIKE == comparison) {
      return cb.like(cb.lower(stringProperty), "%" + value.toLowerCase() + "%");
    }

    if (Comparison.START_LIKE == comparison) {
      return cb.like(stringProperty, value + "%");
    }

    if (Comparison.END_LIKE == comparison) {
      return cb.like(stringProperty, "%" + value);
    }

    if (Comparison.START_ILIKE == comparison) {
      return cb.like(cb.lower(stringProperty), value.toLowerCase() + "%");
    }

    if (Comparison.END_ILIKE == comparison) {
      return cb.like(cb.lower(stringProperty), "%" + value.toLowerCase());
    }

    throw newInvalidComparisonException(comparison, property);
  }
}
