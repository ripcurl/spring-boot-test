package com.kuoni.gts.masterdata.service.criteria.types;

import java.util.Date;

import com.kuoni.gts.masterdata.service.criteria.CriteriaFilterType;
import com.kuoni.services.domain.v1_0.WsSearchFilter;

/** This class is a enumeration that holds the specific filter types for each field type. */
public enum FilterTypes {
  STRING(String.class, new StringFilterType()),
  DATE(Date.class, new DateFilterType()),
  NUMERIC(Number.class, new NumericFilterType()),
  OBJECT(Object.class, new ObjectFilterType());

  private final Class<?> clazz;
  private final CriteriaFilterType<Object> filterType;

  FilterTypes(Class<?> clazz, CriteriaFilterType<?> filterType) {
    this.clazz = clazz;
    this.filterType = (CriteriaFilterType<Object>) filterType;
  }

  /** @return CriteriaFilterType for the specified field based of value type */
  public static CriteriaFilterType<Object> getFilterType(WsSearchFilter searchFilter) {
    Object value = searchFilter.getValue();
    if (value == null) {
      return OBJECT.filterType;
    }
    for (FilterTypes en : values()) {
      if (en.clazz.isAssignableFrom(value.getClass())) {
        return en.filterType;
      }
    }
    return OBJECT.filterType;
  }
}
