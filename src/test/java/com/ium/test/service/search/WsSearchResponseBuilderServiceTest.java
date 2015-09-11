package com.ium.test.service.search;

import com.ium.test.db.entities.ListHeader;
import com.ium.test.rest.wsobjects.WsListSearchResult;
import com.ium.test.rest.wsobjects.WsSearchRequest;
import com.ium.test.rest.wsobjects.WsSearchResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WsSearchResponseBuilderServiceTest {
  private static final String LIST_CODE = "101";

  @InjectMocks private WsSearchResponseBuilderService wsSearchResponseBuilderService;

  @Mock private ConversionService conversionService;

  @Test(expected = IllegalArgumentException.class)
  public void testBuildWsSearchRespWithNullPageContent() throws Exception {
    wsSearchResponseBuilderService.buildWsSearchResp(new WsSearchRequest(), null, WsListSearchResult.class);
  }

  @Test
  public void testBuildWsSearchResp() throws Exception {
    ListHeader listHeader = new ListHeader();
    listHeader.setCode(LIST_CODE);
    List<ListHeader> listHeaders = new ArrayList<>();
    listHeaders.add(listHeader);
    Page<ListHeader> page = new PageImpl<>(listHeaders);
    WsListSearchResult searchResult = new WsListSearchResult();
    searchResult.setCode(LIST_CODE);
    when(conversionService.convert(listHeader, WsListSearchResult.class)).thenReturn(searchResult);

    WsSearchResponse wsSearchResp = wsSearchResponseBuilderService
            .buildWsSearchResp(new WsSearchRequest(), page, WsListSearchResult.class);

    assertNotNull(wsSearchResp);
    assertTrue(wsSearchResp.getTotal() == 1);
    WsListSearchResult convertedWsListSearchResult = (WsListSearchResult) wsSearchResp.getItems().get(0);
    assertNotNull(convertedWsListSearchResult);
    assertEquals(convertedWsListSearchResult.getCode(), searchResult.getCode());
    verify(conversionService, times(1)).convert(listHeader, WsListSearchResult.class);
  }
}