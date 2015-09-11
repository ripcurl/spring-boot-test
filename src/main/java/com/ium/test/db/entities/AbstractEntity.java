package com.ium.test.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

/**
 * Base class to derive entity classes from.
 */
@Getter @Setter
@EqualsAndHashCode(of = "id")
@MappedSuperclass
public class AbstractEntity implements Persistable<String> {
  @Id
  @GeneratedValue(generator = "generator-uuid")
  @GenericGenerator(name = "generator-uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

  @Override
  public boolean isNew() {
    return null == getId();
  }
}
