package com.ium.test.service.search;

import static org.springframework.util.Assert.notNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ium.test.rest.wsobjects.WsSearchRequest;
import com.ium.test.rest.wsobjects.WsSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Persistable;
import org.springframework.stereotype.Service;

/** Search response builder */
@Service
public class WsSearchResponseBuilderService {
  @Autowired
  private ConversionService conversionService;

  public WsSearchResponse buildWsSearchResp(WsSearchRequest searchRequest,
                                            Page<? extends Persistable> page,
                                            Class<? extends Serializable> searchResultClass) {
    notNull(page);
    List<Serializable> items = new ArrayList<>();
    for (Persistable entity : page.getContent()) {
      items.add(conversionService.convert(entity, searchResultClass));
    }
    return buildResponse(searchRequest, items, page.getTotalElements());
  }

  private WsSearchResponse buildResponse(WsSearchRequest searchRequest,
                                         List<Serializable> items, long total) {
    WsSearchResponse searchResult = new WsSearchResponse();
    searchResult.setItems(items);
    searchResult.setSearchRequest(searchRequest);
    searchResult.setTotal((int)total);
    return searchResult;
  }
}
