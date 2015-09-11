package com.kuoni.gts.masterdata.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.kuoni.gts.masterdata.db.entities.ListHeader;
import com.kuoni.gts.masterdata.service.ListService;
import com.kuoni.gts.masterdata.service.search.WsSearchResponseBuilderService;
import com.kuoni.services.domain.v1_0.WsList;
import com.kuoni.services.domain.v1_0.WsListSearchResult;
import com.kuoni.services.domain.v1_0.WsSearchRequest;
import com.kuoni.services.domain.v1_0.WsSearchResponse;

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