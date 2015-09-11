package com.kuoni.gts.masterdata.service.criteria.types;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.kuoni.gts.masterdata.service.criteria.CriteriaFilterType;
import com.kuoni.services.domain.v1_0.Comparison;

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
