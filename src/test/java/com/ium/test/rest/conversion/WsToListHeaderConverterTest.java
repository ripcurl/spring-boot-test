package com.ium.test.rest.conversion;

import com.ium.test.db.entities.ListHeader;
import com.ium.test.rest.wsobjects.ReferencedEntityTypes;
import com.ium.test.rest.wsobjects.WsList;
import com.ium.test.rest.wsobjects.WsReferenced;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WsToListHeaderConverterTest {
  private WsToListHeaderConverter wsToListHeaderConverter = new WsToListHeaderConverter();

  @Test
  public void testConvert() throws Exception {
    WsList wsList = createWsList(true);
    ListHeader convertResult = wsToListHeaderConverter.convert(wsList);

    assertEquals(wsList.getId(), convertResult.getId());
    assertEquals(wsList.getCode(), convertResult.getCode());
    assertEquals(wsList.getOwner().getCode(), convertResult.getOwnerCode());
  }

  @Test
  public void testConvertWithNullType() throws Exception {
    WsList wsList = createWsList(false);

    ListHeader convertResult = wsToListHeaderConverter.convert(wsList);

    assertEquals(wsList.getId(), convertResult.getId());
    assertEquals(wsList.getCode(), convertResult.getCode());
    assertEquals(wsList.getOwner().getCode(), convertResult.getOwnerCode());
    assertNull(wsList.getOwner().getType());
    assertNull(wsList.getEntries().get(0).getType());
  }

  private WsList createWsList(boolean setType) {
    WsList wsList = new WsList();
    wsList.setId("ID");
    wsList.setName("NAME");
    wsList.setCode("CODE");
    WsReferenced owner = new WsReferenced();
    owner.setId("OWNER-ID");
    owner.setName("OWNER-NAME");
    owner.setCode("OWNER-CODE");
    if (setType) {
      owner.setType(ReferencedEntityTypes.RESERVATION);
    }
    wsList.setOwner(owner);
    wsList.setVersion(1L);
    WsReferenced entry = new WsReferenced();
    entry.setId("ID");
    entry.setName("NAME");
    entry.setCode("CODE");
    if (setType) {
      entry.setType(ReferencedEntityTypes.COMPANY);
    }
    wsList.setEntries(Collections.singletonList(entry));
    return wsList;
  }
}