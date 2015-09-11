package com.kuoni.gts.masterdata.service.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import org.hibernate.jpa.criteria.path.SingularAttributePath;

import com.kuoni.services.domain.v1_0.Comparison;

/** class to create Criterion based on the propertyName, the value and the comparison. */
public abstract class CriteriaFilterType<T> {

  /** Returns true when comparison is equals or comparison is null (so, the default comparison is equals) */
  protected final boolean isEqual(Comparison comparison) {
    return comparison == null || Comparison.EQUALS == comparison;
  }

  /** Creates a new exception using the comparison and propertyName defined. */
  protected final IllegalArgumentException newInvalidComparisonException(Comparison comparison,
                                                                         Expression<? extends Comparable> property) {
    return new IllegalArgumentException("Invalid comparison [" + comparison + "] for field "
        + ((SingularAttributePath) property).getAttribute().getName());
  }

  /** Method to create a Criterion that represents the filter to be apply to the criteria */
  public abstract Predicate createPredicate(Expression<? extends Comparable> propertyName,
                                            T value, Comparison comparison, CriteriaBuilder cb);
}
