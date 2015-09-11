package com.ium.test.rest.conversion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ium.test.db.entities.ListEntry;
import com.ium.test.db.entities.ListHeader;
import com.ium.test.rest.wsobjects.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * <b>Description:</b> This class represent converter from WS to entity
 */
@Component
public class ListHeaderToWsConverter implements Converter<ListHeader, WsList> {
  @Override
  public WsList convert(ListHeader source) {
    WsList wsList = new WsList();
    wsList.setId(source.getId());
    wsList.setName(source.getName());
    wsList.setCode(source.getCode());
    wsList.setVersion(source.getVersion());
    wsList.setCreated(createUserAndData(source.getCreated(), source.getCreatedBy()));
    wsList.setLastUpdate(createUserAndData(source.getLastUpdated(), source.getUpdatedBy()));
    wsList.setOwner(createOwner(source.getOwnerId(), source.getOwnerName(), source.getOwnerCode(), source.getOwnerType()));
    wsList.setEntries(createEntries(source.getEntries()));
    return wsList;
  }

  private WsUserAndDate createUserAndData(Date date, String userName) {
    WsUserAndDate userAndDate = new WsUserAndDate();
    userAndDate.setDate(date);
    WsReferencedUser createdUser = new WsReferencedUser();
    createdUser.setCode(userName);
    createdUser.setName(userName);
    createdUser.setType(ReferencedEntityTypes.USER);
    userAndDate.setUser(createdUser);
    return userAndDate;
  }

  private WsReferenced createOwner(String ownerId, String ownerName, String ownerCode, String ownerType) {
    WsReferenced wsReferenced = new WsReferenced();
    wsReferenced.setId(ownerId);
    wsReferenced.setName(ownerName);
    wsReferenced.setCode(ownerCode);
    wsReferenced.setType(ReferencedEntityTypes.fromValue(ownerType));
    return wsReferenced;
  }

  private List<WsReferenced> createEntries(List<ListEntry> entries) {
    List<WsReferenced> wsEntries = new ArrayList<>();

    for (ListEntry entry : entries) {
      WsReferenced wsReferenced = new WsReferenced();
      wsReferenced.setId(entry.getId());
      wsReferenced.setName(entry.getName());
      wsReferenced.setCode(entry.getCode());
      wsReferenced.setType(ReferencedEntityTypes.fromValue(entry.getType()));
      wsEntries.add(wsReferenced);
    }
    return wsEntries;
  }
}
