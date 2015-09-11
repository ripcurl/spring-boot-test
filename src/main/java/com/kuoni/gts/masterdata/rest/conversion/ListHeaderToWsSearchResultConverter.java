package com.kuoni.gts.masterdata.rest.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.kuoni.gts.masterdata.db.entities.ListHeader;
import com.kuoni.services.domain.v1_0.WsListSearchResult;

/** Converter from listHeader to WsListSearchResult */
@Component
public class ListHeaderToWsSearchResultConverter implements Converter<ListHeader, WsListSearchResult> {

  /** converts resource entity to WS search result. */
  @Override
  public WsListSearchResult convert(ListHeader source) {
    if (source == null) {
      return new WsListSearchResult();
    }
    WsListSearchResult listSearchResult = new WsListSearchResult();
    listSearchResult.setId(source.getId());
    listSearchResult.setName(source.getName());
    listSearchResult.setCode(source.getCode());
    listSearchResult.setOwnerId(source.getOwnerId());
    listSearchResult.setOwnerName(source.getOwnerName());
    listSearchResult.setOwnerCode(source.getOwnerCode());
    listSearchResult.setOwnerType(source.getOwnerType());
    listSearchResult.setLastUpdateDate(source.getLastUpdated());
    listSearchResult.setLastUpdateUserId(source.getUpdatedBy());
    listSearchResult.setLastUpdateUserName(source.getUpdatedBy());
    return listSearchResult;
  }
}
