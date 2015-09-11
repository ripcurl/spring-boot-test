package com.ium.test.service.search;

import com.ium.test.db.entities.AbstractEntity;
import com.ium.test.rest.wsobjects.SortOrder;
import com.ium.test.rest.wsobjects.WsSearchRequest;
import com.ium.test.rest.wsobjects.WsSearchSorter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WsSearchServiceTest {

  private static final int PAGE = 10;
  private static final int LIMIT = 100;
  private static final String CREATED_BY = "createdBy";
  private WsSearchService wsSearchService = new WsSearchService();

  @Mock private JpaSpecificationExecutor<AbstractEntity> repository;

  @Test
  public void testSearchWithNullPageAndLimit() throws Exception {
    WsSearchRequest wsSearchRequest = new WsSearchRequest();
    BaseSearchSpecification<AbstractEntity> baseSearchSpecification = new BaseSearchSpecification<>(wsSearchRequest.getFilters());

    wsSearchService.search(repository, wsSearchRequest, baseSearchSpecification);

    verify(repository).findAll(baseSearchSpecification,
            new PageRequest(WsSearchService.DEFAULT_PAGE_NUMBER, WsSearchService.DEFAULT_NUMBER_PER_PAGE));
  }

  @Test
  public void testSearchWithSpecifiedPage() throws Exception {
    WsSearchRequest wsSearchRequest = new WsSearchRequest();
    wsSearchRequest.setPage(PAGE);
    BaseSearchSpecification<AbstractEntity> baseSearchSpecification = new BaseSearchSpecification<>(wsSearchRequest.getFilters());

    wsSearchService.search(repository, wsSearchRequest, baseSearchSpecification);

    verify(repository).findAll(baseSearchSpecification,
            new PageRequest(PAGE - 1, WsSearchService.DEFAULT_NUMBER_PER_PAGE));
  }

  @Test
  public void testSearchWithSpecifiedLimit() throws Exception {
    WsSearchRequest wsSearchRequest = new WsSearchRequest();
    wsSearchRequest.setLimit(LIMIT);
    BaseSearchSpecification<AbstractEntity> baseSearchSpecification = new BaseSearchSpecification<>(wsSearchRequest.getFilters());

    wsSearchService.search(repository, wsSearchRequest, baseSearchSpecification);

    verify(repository).findAll(baseSearchSpecification,
            new PageRequest(WsSearchService.DEFAULT_PAGE_NUMBER, LIMIT));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSearchWithExceedingLimit() throws Exception {
    WsSearchRequest wsSearchRequest = new WsSearchRequest();
    wsSearchRequest.setLimit(WsSearchService.MAX_NUMBER_PER_PAGE + 1);
    BaseSearchSpecification<AbstractEntity> baseSearchSpecification = new BaseSearchSpecification<>(wsSearchRequest.getFilters());

    wsSearchService.search(repository, wsSearchRequest, baseSearchSpecification);
  }

  @Test
  public void testSearchWithNullSortOrder() throws Exception {
    WsSearchRequest wsSearchRequest = new WsSearchRequest();
    BaseSearchSpecification<AbstractEntity> baseSearchSpecification = new BaseSearchSpecification<>(wsSearchRequest.getFilters());

    wsSearchService.search(repository, wsSearchRequest, baseSearchSpecification);

    verify(repository).findAll(baseSearchSpecification,
            new PageRequest(WsSearchService.DEFAULT_PAGE_NUMBER, WsSearchService.DEFAULT_NUMBER_PER_PAGE, null));
  }

  @Test
  public void testSearchWithEmptySortOrder() throws Exception {
    WsSearchRequest wsSearchRequest = new WsSearchRequest();
    wsSearchRequest.setSorters(new ArrayList<>());
    BaseSearchSpecification<AbstractEntity> baseSearchSpecification = new BaseSearchSpecification<>(wsSearchRequest.getFilters());

    wsSearchService.search(repository, wsSearchRequest, baseSearchSpecification);

    verify(repository).findAll(baseSearchSpecification,
            new PageRequest(WsSearchService.DEFAULT_PAGE_NUMBER, WsSearchService.DEFAULT_NUMBER_PER_PAGE, null));
  }

  @Test
  public void testSearchWithSortOrder() throws Exception {
    WsSearchRequest wsSearchRequest = new WsSearchRequest();
    List<WsSearchSorter> wsSearchSorterList = new ArrayList<>();
    WsSearchSorter wsSearchSorter = new WsSearchSorter();
    wsSearchSorter.setName(CREATED_BY);
    wsSearchSorter.setOrder(SortOrder.ASCENDING);
    wsSearchSorterList.add(wsSearchSorter);
    wsSearchRequest.setSorters(wsSearchSorterList);
    BaseSearchSpecification<AbstractEntity> baseSearchSpecification = new BaseSearchSpecification<>(wsSearchRequest.getFilters());

    wsSearchService.search(repository, wsSearchRequest, baseSearchSpecification);

    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Sort.Direction.ASC, CREATED_BY));
    verify(repository).findAll(baseSearchSpecification,
            new PageRequest(WsSearchService.DEFAULT_PAGE_NUMBER, WsSearchService.DEFAULT_NUMBER_PER_PAGE, new Sort(orders)));
  }

  @Test
  public void testSearchCountOnly() throws Exception {
    WsSearchRequest wsSearchRequest = new WsSearchRequest();
    wsSearchRequest.setCountOnly(true);
    BaseSearchSpecification<AbstractEntity> baseSearchSpecification = new BaseSearchSpecification<>(wsSearchRequest.getFilters());

    wsSearchService.search(repository, wsSearchRequest, baseSearchSpecification);

    verify(repository).count(baseSearchSpecification);
  }
}