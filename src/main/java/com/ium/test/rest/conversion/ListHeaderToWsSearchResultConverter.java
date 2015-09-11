package com.ium.test.rest.conversion;

import com.ium.test.rest.wsobjects.WsListSearchResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ium.test.db.entities.ListHeader;

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
