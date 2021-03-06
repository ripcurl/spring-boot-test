package com.ium.test.rest.conversion;

import com.ium.test.db.entities.ListEntry;
import com.ium.test.db.entities.ListHeader;
import com.ium.test.rest.wsobjects.WsList;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ListHeaderToWsConverterTest {
  private ListHeaderToWsConverter converter = new ListHeaderToWsConverter();

  @Test
  public void testConvert() throws Exception {
    ListHeader listHeader = createListHeader();

    WsList convertResult = converter.convert(listHeader);

    assertEquals(listHeader.getId(), convertResult.getId());
    assertEquals(listHeader.getCode(), convertResult.getCode());
    assertEquals(listHeader.getOwnerCode(), convertResult.getOwner().getCode());
    assertEquals(listHeader.getCreatedBy(), convertResult.getCreated().getUser().getCode());
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