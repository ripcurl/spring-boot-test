package com.kuoni.gts.masterdata.rest.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.kuoni.gts.masterdata.db.entities.ListEntry;
import com.kuoni.gts.masterdata.db.entities.ListHeader;
import com.kuoni.services.domain.v1_0.ReferencedEntityTypes;
import com.kuoni.services.domain.v1_0.WsList;
import com.kuoni.services.domain.v1_0.WsReferenced;

/**
 * <b>Description:</b> This class represent converter from WS to entity
 */
@Component
public class WsToListHeaderConverter implements Converter<WsList, ListHeader> {
  @Override
  public ListHeader convert(WsList source) {
    ListHeader listHeader = new ListHeader();
    listHeader.setId(source.getId());
    listHeader.setName(source.getName());
    listHeader.setCode(source.getCode());
    listHeader.setVersion(source.getVersion());
    listHeader.setOwnerId(source.getOwner().getId());
    listHeader.setOwnerName(source.getOwner().getName());
    listHeader.setOwnerCode(source.getOwner().getCode());
    ReferencedEntityTypes type = source.getOwner().getType();
    if (type != null) {
      listHeader.setOwnerType(type.value());
    }
    listHeader.setEntries(createEntries(source.getEntries()));
    return listHeader;
  }

  private List<ListEntry> createEntries(List<WsReferenced> entries) {
    List<ListEntry> listEntries = new ArrayList<>();

    for (WsReferenced referenced : entries) {
      ListEntry entry = new ListEntry();
      entry.setId(referenced.getId());
      entry.setName(referenced.getName());
      entry.setCode(referenced.getCode());
      ReferencedEntityTypes type = referenced.getType();
      if (type != null) {
        entry.setType(type.value());
      }
      listEntries.add(entry);
    }
    return listEntries;
  }
}
