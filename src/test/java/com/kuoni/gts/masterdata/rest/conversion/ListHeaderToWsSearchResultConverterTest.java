package com.kuoni.gts.masterdata.rest.conversion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.Date;

import org.junit.Test;

import com.kuoni.gts.masterdata.db.entities.ListEntry;
import com.kuoni.gts.masterdata.db.entities.ListHeader;
import com.kuoni.services.domain.v1_0.WsListSearchResult;

public class ListHeaderToWsSearchResultConverterTest {
  private ListHeaderToWsSearchResultConverter converter = new ListHeaderToWsSearchResultConverter();

  @Test
  public void testConvert() throws Exception {
    ListHeader listHeader = createListHeader();

    WsListSearchResult convertResult = converter.convert(listHeader);

    assertEquals(listHeader.getId(), convertResult.getId());
    assertEquals(listHeader.getCode(), convertResult.getCode());
    assertEquals(listHeader.getOwnerCode(), convertResult.getOwnerCode());
    assertEquals(listHeader.getLastUpdated(), convertResult.getLastUpdateDate());
  }

  @Test
  public void testConvertNullSource() throws Exception {
    WsListSearchResult convertResult = converter.convert(null);

    assertNull(convertResult.getCode());
  }

  private ListHeader createListHeader() {
    ListHeader listHeader = new ListHeader();
    listHeader.setId("ID");
    listHeader.setVersion(1L);
    listHeader.setCode("CODE");
    listHeader.setName("NAME");
    listHeader.setOwnerId("OWNER-ID");
    listHeader.setOwnerCode("OWNER-CODE");
    listHeader.setOwnerName("OWNER-NAME");
    listHeader.setOwnerType("reservation");
    listHeader.setCreated(new Date());
    listHeader.setCreatedBy("TEST-USER");
    listHeader.setLastUpdated(new Date());
    listHeader.setUpdatedBy("TEST-USER");
    ListEntry entry = new ListEntry();
    entry.setId("ENTRY-ID");
    entry.setName("ENTRY-NAME");
    entry.setCode("ENTRY-CODE");
    entry.setType("company");
    listHeader.setEntries(Collections.singletonList(entry));
    return listHeader;
  }
}