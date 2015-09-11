package com.kuoni.gts.masterdata.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.kuoni.gts.masterdata.Application;
import com.kuoni.gts.masterdata.db.entities.ListHeader_;
import com.kuoni.services.domain.v1_0.ReferencedEntityTypes;
import com.kuoni.services.domain.v1_0.WsErrorResponse;
import com.kuoni.services.domain.v1_0.WsList;
import com.kuoni.services.domain.v1_0.WsReferenced;
import com.kuoni.services.domain.v1_0.WsSearchFilter;
import com.kuoni.services.domain.v1_0.WsSearchRequest;
import com.kuoni.services.domain.v1_0.WsSearchResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port=0")
@WebAppConfiguration
@ActiveProfiles(profiles = "integrationTest")
public class ITListRestService {
  public static final String SERVER_HOST = "http://localhost:";
  public static final String APPLICATION_TYPE = "application/vnd.list.v01.00+xml";

  @Value("${local.server.port}")
  private int port;
  private RestTemplate restTemplate = new TestRestTemplate();
  private String server;
  private HttpEntity<WsList> listEntity = new HttpEntity<>(new WsList(), getHttpHeader());

  @Before
  public void setUp() throws Exception {
    server = SERVER_HOST + port + "/list";
  }

  @Test
  public void testGetListById() {
    HttpEntity<WsList> wsListEntity = new HttpEntity<>(createWsList(), getHttpHeader());

    ResponseEntity<WsList> putResponse = restTemplate
        .exchange(server, HttpMethod.PUT, wsListEntity, WsList.class);

    assertEquals(HttpStatus.OK, putResponse.getStatusCode());
    assertNotNull(putResponse.getBody().getId());

    ResponseEntity<WsList> getResponse = restTemplate
        .exchange(server + "/" + putResponse.getBody().getId(), HttpMethod.GET, listEntity, WsList.class);
    
    assertEquals(HttpStatus.OK, getResponse.getStatusCode());
    assertEquals(putResponse.getBody().getCode(), getResponse.getBody().getCode());
  }

  @Test
  public void testSaveAndUpdateList() {
    ResponseEntity<WsList> saveResponse = restTemplate
        .exchange(server, HttpMethod.PUT, new HttpEntity<>(createWsList(), getHttpHeader()), WsList.class);

    assertEquals(HttpStatus.OK, saveResponse.getStatusCode());
    assertNotNull(saveResponse.getBody().getId());

    WsList wsList = saveResponse.getBody();
    WsReferenced entry1 = wsList.getEntries().get(0);
    WsReferenced entry2 = wsList.getEntries().get(1);
    wsList.setEntries(Arrays.asList(entry2, entry1));

    ResponseEntity<WsList> updateResponse = restTemplate
        .exchange(server, HttpMethod.PUT, new HttpEntity<>(wsList, getHttpHeader()), WsList.class);

    assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
    assertNotNull(updateResponse.getBody().getId());
    assertEquals(entry2.getCode(), updateResponse.getBody().getEntries().get(0).getCode());
  }

  @Test
  public void testDeleteList() throws Exception {
    ResponseEntity<WsList> putResponse = restTemplate
            .exchange(server, HttpMethod.PUT, new HttpEntity<>(createWsList(), getHttpHeader()), WsList.class);

    ResponseEntity<String> deleteResponse = restTemplate
            .exchange(server + "/" + putResponse.getBody().getId(), HttpMethod.DELETE, listEntity, String.class);

    assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
    assertEquals(putResponse.getBody().getId(), deleteResponse.getBody());

    ResponseEntity<WsErrorResponse> getResponse = restTemplate
            .exchange(server + "/" + deleteResponse.getBody(), HttpMethod.GET, listEntity, WsErrorResponse.class);

    assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
  }

  @Test
  public void testSearch() throws Exception {
    ResponseEntity<WsList> putResponse = restTemplate
            .exchange(server, HttpMethod.PUT, new HttpEntity<>(createWsList(), getHttpHeader()), WsList.class);

    WsSearchRequest searchRequest = new WsSearchRequest();
    WsSearchFilter filter = new WsSearchFilter();
    filter.setName(ListHeader_.ownerCode.getName());
    filter.setValue(putResponse.getBody().getOwner().getCode());
    searchRequest.setFilters(Collections.singletonList(filter));

    ResponseEntity<WsSearchResponse> searchResponse = restTemplate
            .exchange(server + "/search", HttpMethod.POST,
                      new HttpEntity<>(searchRequest, getHttpHeader()), WsSearchResponse.class);

    assertEquals(HttpStatus.OK, searchResponse.getStatusCode());
    assertEquals(Integer.valueOf(1), searchResponse.getBody().getTotal());
  }

  private HttpHeaders getHttpHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", APPLICATION_TYPE);
    headers.add("Accept", APPLICATION_TYPE);
    return headers;
  }

  private WsList createWsList() {
    WsList wsList = new WsList();
    wsList.setCode(UUID.randomUUID().toString());
    wsList.setOwner(createWsReferenced(UUID.randomUUID().toString(), ReferencedEntityTypes.RESERVATION));
    WsReferenced entry1 = createWsReferenced("ENTRY-CODE-1", ReferencedEntityTypes.COMPANY);
    WsReferenced entry2 = createWsReferenced("ENTRY-CODE-2", ReferencedEntityTypes.COMPANY);
    wsList.setEntries(Arrays.asList(entry1, entry2));
    return wsList;
  }

  private WsReferenced createWsReferenced(String code, ReferencedEntityTypes type) {
    WsReferenced entry = new WsReferenced();
    entry.setCode(code);
    entry.setType(type);
    return entry;
  }
}