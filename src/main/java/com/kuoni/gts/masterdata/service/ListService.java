package com.kuoni.gts.masterdata.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.kuoni.gts.masterdata.db.entities.ListHeader;
import com.kuoni.gts.masterdata.db.enums.Status;
import com.kuoni.gts.masterdata.db.repositories.ListHeaderRepository;
import com.kuoni.gts.masterdata.service.search.ListSearchSpecification;
import com.kuoni.gts.masterdata.service.search.WsSearchService;
import com.kuoni.services.domain.v1_0.WsSearchRequest;

/** <b>Description:</b> This class represent list service */
@Service
public class ListService {
  @Autowired
  private ListHeaderRepository listHeaderRepository;
  @Autowired
  private WsSearchService searchService;

  /** Get ListEntity by id */
  public ListHeader getListById(String id) {
    ListHeader listHeader = listHeaderRepository.findOne(id);

    if (listHeader == null) {
      throw new EntityNotFoundException("List with ID=" + id + " not found.");
    }

    return listHeader;
  }

  /** Save LisHeader entity */
  public ListHeader saveList(ListHeader listHeader) {
    if (listHeader.getId() != null) {
      getListById(listHeader.getId());
    }

    listHeaderRepository.save(listHeader);
    return listHeaderRepository.findOne(listHeader.getId());
  }

  /** Search */
  public Page<ListHeader> search(WsSearchRequest searchRequest) {
    return searchService.search(listHeaderRepository, searchRequest,
            new ListSearchSpecification(searchRequest.getFilters()));
  }

  /** Make ListHeader entity INACTIVE by id */
  public void delete(String id) {
    ListHeader listHeader = getListById(id);

    listHeader.setStatus(Status.INACTIVE);
    listHeaderRepository.save(listHeader);
  }
}
