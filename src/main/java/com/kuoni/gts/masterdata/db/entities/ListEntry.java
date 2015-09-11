package com.kuoni.gts.masterdata.db.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** <b>Description:</b> This class represent list entry entity */
@Getter @Setter
@Table(name = "list_entry")
@Entity
public class ListEntry extends AbstractEntity {
  @Column(name = "name")
  private String name;

  @Column(name = "code", nullable = false)
  private String code;

  @Column(name = "type", nullable = false)
  private String type;
}
