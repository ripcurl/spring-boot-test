package com.kuoni.gts.masterdata.service.criteria.types;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.kuoni.gts.masterdata.service.criteria.CriteriaFilterType;
import com.kuoni.services.domain.v1_0.Comparison;

/**
 * Dates filterType. Supports below comparisons:
 * EQUALS; NOT_EQUALS;
 * LESSER_THAN; GREATER_THAN;
 * LESSER_THAN_OR_EQUALS; GREATER_THAN_OR_EQUALS;
 *
 */
class DateFilterType extends CriteriaFilterType<Date> {

  @Override
  public Predicate createPredicate(Expression<? extends Comparable> property, Date value,
                                   Comparison comparison, CriteriaBuilder cb) {
    Expression<? extends Date> dateProperty = (Expression<? extends Date>) property;

    if (isEqual(comparison)) {
      return cb.equal(dateProperty, value);
    } else if (comparison == Comparison.LESSER_THAN_OR_EQUALS) {
      return cb.lessThanOrEqualTo(dateProperty, value);
    } else if (comparison == Comparison.GREATER_THAN_OR_EQUALS) {
      return cb.greaterThanOrEqualTo(dateProperty, value);
    } else if (comparison == Comparison.LESSER_THAN) {
      return cb.lessThan(dateProperty, value);
    } else if (comparison == Comparison.GREATER_THAN) {
      return cb.greaterThan(dateProperty, value);
    } else if (comparison == Comparison.NOT_EQUALS) {
      return cb.notEqual(dateProperty, value);
    }

    throw newInvalidComparisonException(comparison, property);
  }
}
