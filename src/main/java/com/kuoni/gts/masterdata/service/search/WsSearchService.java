package com.kuoni.gts.masterdata.service.search;

import static org.springframework.util.Assert.notNull;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.kuoni.services.domain.v1_0.WsSearchRequest;
import com.kuoni.services.domain.v1_0.WsSearchSorter;

/** <b>Description:</b> This class represents entity search methods */
@Service
@Slf4j
public class WsSearchService {
  public static final int MAX_NUMBER_PER_PAGE = 256;
  public static final int DEFAULT_NUMBER_PER_PAGE = 10;

  public static final int DEFAULT_PAGE_NUMBER = 0;

  private static final ImmutableMap<String, Sort.Direction> ORDER_MAP = ImmutableMap.of(
      "ASCENDING", Sort.Direction.ASC, "DESCENDING", Sort.Direction.DESC);

  public <T extends Persistable> Page<T> search(JpaSpecificationExecutor<T> repo,
                                                WsSearchRequest searchRequest,
                                                BaseSearchSpecification<T> specification) {
    log.trace("search request: {}", searchRequest);
    notNull(searchRequest);

    Pageable pageInfo = getPageInfo(searchRequest);
    if (searchRequest.isCountOnly()) {
      long count = repo.count(specification);
      return new PageImpl<>(new ArrayList<>(), pageInfo, count);
    } else {
      return repo.findAll(specification, pageInfo);
    }
  }

  /** Get paging parameters from {@link com.kuoni.services.domain.v1_0.WsSearchRequest} */
  private Pageable getPageInfo(WsSearchRequest request) {
    int pageNumber = request.getPage() != null ? request.getPage() - 1 : DEFAULT_PAGE_NUMBER;

    if (request.getLimit() != null && request.getLimit() > MAX_NUMBER_PER_PAGE) {
      throw new IllegalArgumentException(String.format("Search request limit '%d' exceeds max limit '%d'",
          request.getLimit(), MAX_NUMBER_PER_PAGE));
    }
    int pageSize = request.getLimit() != null ? request.getLimit() : DEFAULT_NUMBER_PER_PAGE;

    return new PageRequest(pageNumber, pageSize, buildSortOrders(request.getSorters()));
  }

  private static Sort buildSortOrders(List<WsSearchSorter> sorters) {
    if (sorters == null || sorters.isEmpty()) {
      return null;
    }
    List<Sort.Order> orders = new ArrayList<>(sorters.size());
    orders.addAll(sorters.stream()
            .map(sorter -> new Sort.Order(ORDER_MAP.get(sorter.getOrder().value()), sorter.getName()))
            .collect(Collectors.toList()));
    return new Sort(orders);
  }

}
