package com.kuoni.gts.masterdata.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kuoni.gts.masterdata.db.entities.ListHeader;

/**
 * <b>Description:</b> This class is a data access layer for ListHeader entity
 */
public interface ListHeaderRepository extends JpaRepository<ListHeader, String>, JpaSpecificationExecutor<ListHeader> {
}
