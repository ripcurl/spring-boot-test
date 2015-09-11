package com.ium.test.rest;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

import com.ium.test.rest.wsobjects.WsList;
import com.ium.test.rest.wsobjects.WsListSearchResult;
import com.ium.test.rest.wsobjects.WsSearchRequest;
import com.ium.test.rest.wsobjects.WsSearchResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.ium.test.db.entities.ListHeader;
import com.ium.test.service.ListService;
import com.ium.test.service.search.WsSearchResponseBuilderService;

/** This class represents application information rest service */
@Component
@Path("/list")
@Produces({"application/vnd.list.v01.00+xml", "application/vnd.list.v01.00+json"})
@Consumes({"application/vnd.list.v01.00+xml", "application/vnd.list.v01.00+json"})
@Slf4j
public class ListRestService {
  public static final String ID = "id";

  @Autowired private ConversionService conversionService;
  @Autowired private ListService listService;
  @Autowired private WsSearchResponseBuilderService searchResponseBuilder;

  /** Get list by id */
  @GET
  @Path("/{id}")
  public Response getList(@PathParam(ID) String id) throws IOException {
    log.debug("Getting list by - " + id);
    hasText(id);

    listService.getListById(id);

    return Response.ok(conversionService.convert(listService.getListById(id), WsList.class)).build();
  }

  /** Save list */
  @PUT
  public Response saveList(WsList wsList) throws IOException {
    ListHeader listHeader = listService.saveList(conversionService.convert(wsList, ListHeader.class));
    return Response.ok(conversionService.convert(listHeader, WsList.class)).build();
  }

  /** Search list */
  @POST
  @Path("/search")
  public Response search(WsSearchRequest searchRequest) throws IOException {
    notNull(searchRequest, "request must not be null");

    Page<ListHeader> page = listService.search(searchRequest);
    WsSearchResponse searchResponse =
            searchResponseBuilder.buildWsSearchResp(searchRequest, page, WsListSearchResult.class);

    log.info("reservation search return {} items for {} total",
            searchResponse.getItems().size(), searchResponse.getTotal());

    return Response.ok(searchResponse).build();
  }

  /** Delete list by id */
  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam(ID) String id) {
    log.debug("LIST - delete: id = {}", id);
    if (Strings.isNullOrEmpty(id)) {
      throw new IllegalArgumentException("ID must not be empty");
    }

    listService.delete(id);

    return Response.ok(id).build();
  }
}
