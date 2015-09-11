package com.ium.test.service.criteria.types;

import com.ium.test.rest.wsobjects.Comparison;
import com.ium.test.rest.wsobjects.WsSearchFilter;
import com.ium.test.service.criteria.CriteriaFilterType;
import org.junit.Test;

import java.io.Serializable;
import java.util.Date;

import static org.junit.Assert.assertEquals;

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