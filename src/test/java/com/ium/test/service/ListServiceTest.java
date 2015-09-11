package com.ium.test.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ium.test.db.entities.ListHeader;
import com.ium.test.db.repositories.ListHeaderRepository;
import com.ium.test.service.search.WsSearchService;

@RunWith(MockitoJUnitRunner.class)
public class ListServiceTest {
  @Mock
  private ListHeaderRepository listHeaderRepository;
  @Mock
  private WsSearchService searchService;
  @InjectMocks
  private ListService listService;

  @Test
  public void testGetListById() throws Exception {
    String id = "id";
    when(listHeaderRepository.findOne(id)).thenReturn(new ListHeader());

    listService.getListById(id);

    verify(listHeaderRepository).findOne(id);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testGetListByIdWithException() throws Exception {
    listService.getListById("id");
  }

  @Test
  public void testSaveUpdateList() throws Exception {
    ListHeader listHeader = new ListHeader();
    listHeader.setId("id");
    when(listHeaderRepository.findOne(anyString())).thenReturn(new ListHeader());

    listService.saveList(listHeader);

    verify(listHeaderRepository).save(listHeader);
    verify(listHeaderRepository, times(2)).findOne(listHeader.getId());
  }

  @Test
  public void testSaveList() throws Exception {
    ListHeader listHeader = new ListHeader();
    when(listHeaderRepository.findOne(anyString())).thenReturn(new ListHeader());

    listService.saveList(listHeader);

    verify(listHeaderRepository).save(listHeader);
    verify(listHeaderRepository).findOne(listHeader.getId());
  }

  @Test
  public void testDelete() throws Exception {
    String id = "id";
    ListHeader listHeader = new ListHeader();
    when(listHeaderRepository.findOne(id)).thenReturn(listHeader);

    listService.delete(id);

    verify(listHeaderRepository).save(listHeader);
  }
}