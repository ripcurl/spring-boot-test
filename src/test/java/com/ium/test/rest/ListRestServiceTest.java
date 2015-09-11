package com.ium.test.rest;

import com.ium.test.db.entities.ListHeader;
import com.ium.test.rest.wsobjects.WsList;
import com.ium.test.rest.wsobjects.WsListSearchResult;
import com.ium.test.rest.wsobjects.WsSearchRequest;
import com.ium.test.rest.wsobjects.WsSearchResponse;
import com.ium.test.service.ListService;
import com.ium.test.service.search.WsSearchResponseBuilderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.ws.rs.core.Response;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListRestServiceTest {

  @Mock private ConversionService conversionService;
  @Mock private ListService listService;
  @Mock private WsSearchResponseBuilderService searchResponseBuilder;
  @InjectMocks private ListRestService listRestService;

  @Test
  public void testGetList() throws Exception {
    when(listService.getListById(anyString())).thenReturn(new ListHeader());
    when(conversionService.convert(any(ListHeader.class), eq(WsList.class))).thenReturn(new WsList());

    Response response = listRestService.getList("id");

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void testSaveList() throws Exception {
    when(listService.saveList(new ListHeader())).thenReturn(new ListHeader());
    when(conversionService.convert(any(WsList.class), eq(ListHeader.class))).thenReturn(new ListHeader());
    when(conversionService.convert(any(ListHeader.class), eq(WsList.class))).thenReturn(new WsList());

    Response response = listRestService.saveList(new WsList());

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void testSearch() throws Exception {
    WsSearchResponse wsSearchResponse = new WsSearchResponse();
    wsSearchResponse.setItems(Collections.singletonList(new WsListSearchResult()));

    when(listService.search(any(WsSearchRequest.class)))
            .thenReturn(new PageImpl<>(Collections.singletonList(new ListHeader())));
    when(searchResponseBuilder.buildWsSearchResp(any(WsSearchRequest.class), any(Page.class), eq(WsListSearchResult.class)))
            .thenReturn(wsSearchResponse);

    Response response = listRestService.search(new WsSearchRequest());

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void testDelete() throws Exception {
    Response response = listRestService.delete("id");

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteWithEmptyId() throws Exception {
    listRestService.delete("");
  }

}