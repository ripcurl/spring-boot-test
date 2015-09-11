package com.kuoni.gts.masterdata.service.criteria.types;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.Date;

import org.junit.Test;

import com.kuoni.gts.masterdata.service.criteria.CriteriaFilterType;
import com.kuoni.services.domain.v1_0.Comparison;
import com.kuoni.services.domain.v1_0.WsSearchFilter;

public class FilterTypesTest {

  @Test
  public void testGetFilterType() {
    CriteriaFilterType<Object> type = FilterTypes.getFilterType(createSearchFilter());
    assertEquals(StringFilterType.class, type.getClass());

    WsSearchFilter numericFilter = createSearchFilter();
    numericFilter.setValue(123);
    type = FilterTypes.getFilterType(numericFilter);
    assertEquals(NumericFilterType.class, type.getClass());

    WsSearchFilter dateFilter = createSearchFilter();
    dateFilter.setValue(new Date());
    type = FilterTypes.getFilterType(dateFilter);
    assertEquals(DateFilterType.class, type.getClass());

    WsSearchFilter otherFilter = createSearchFilter();
    otherFilter.setValue(new Serializable() {});
    type = FilterTypes.getFilterType(otherFilter);
    assertEquals(ObjectFilterType.class, type.getClass());
  }

  @Test
  public void testGetFilterTypeNullValue() {
    CriteriaFilterType<Object> type = FilterTypes.getFilterType(new WsSearchFilter());
    assertEquals(ObjectFilterType.class, type.getClass());
  }

  private WsSearchFilter createSearchFilter() {
    WsSearchFilter stringFilter = new WsSearchFilter();
    stringFilter.setName("");
    stringFilter.setValue("");
    stringFilter.setComparison(Comparison.EQUALS);
    return stringFilter;
  }
}