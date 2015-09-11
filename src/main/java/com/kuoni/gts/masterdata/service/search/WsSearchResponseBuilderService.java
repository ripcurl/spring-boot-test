package com.kuoni.gts.masterdata.service.search;

import static org.springframework.util.Assert.notNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Persistable;
import org.springframework.stereotype.Service;

import com.kuoni.services.domain.DomainBusinessObject;
import com.kuoni.services.domain.v1_0.WsSearchRequest;
import com.kuoni.services.domain.v1_0.WsSearchResponse;

/** Search response builder */
@Service
public class WsSearchResponseBuilderService {
  @Autowired
  private ConversionService conversionService;

  public WsSearchResponse buildWsSearchResp(WsSearchRequest searchRequest,
                                            Page<? extends Persistable> page,
                                            Class<? extends DomainBusinessObject> searchResultClass) {
    notNull(page);
    List<DomainBusinessObject> items = new ArrayList<>();
    for (Persistable entity : page.getContent()) {
      items.add(conversionService.convert(entity, searchResultClass));
    }
    return buildResponse(searchRequest, items, page.getTotalElements());
  }

  private WsSearchResponse buildResponse(WsSearchRequest searchRequest,
                                         List<DomainBusinessObject> items, long total) {
    WsSearchResponse searchResult = new WsSearchResponse();
    searchResult.setItems(items);
    searchResult.setSearchRequest(searchRequest);
    searchResult.setTotal((int)total);
    return searchResult;
  }

}
