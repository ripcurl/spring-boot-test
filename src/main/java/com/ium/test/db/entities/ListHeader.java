package com.ium.test.db.entities;

import com.ium.test.db.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Where;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;

/** <b>Description:</b> This class represent list header entity */
@Getter @Setter
@Table(name = "list_header")
@Where(clause = "status <> 'INACTIVE'")
@Entity
public class ListHeader extends AbstractEntity implements Auditable<String, String> {
  @Column(name = "name")
  private String name;

  @Column(name = "code")
  private String code;

  @Version
  @Column(name = "version")
  private Long version;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created", updatable = false)
  private Date created;

  @CreatedBy
  @Column(name = "created_by", updatable = false)
  private String createdBy;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_updated")
  private Date lastUpdated;

  @LastModifiedBy
  @Column(name = "updated_by")
  private String updatedBy;

  @Column(name = "owner_id")
  private String ownerId;

  @Column(name = "owner_name")
  private String ownerName;

  @Column(name = "owner_code", nullable = false)
  private String ownerCode;

  @Column(name = "owner_type", nullable = false)
  private String ownerType;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private Status status = Status.ACTIVE;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name="list_header_id", referencedColumnName = "id", updatable = true, nullable = false)
  @OrderColumn(name = "entry_sequence")
  private List<ListEntry> entries = new ArrayList<>();

  public DateTime getCreatedDate() {
    return null == created ? null : new DateTime(created);
  }

  public void setCreatedDate(final DateTime createdDate) {
    this.created = null == createdDate ? null : createdDate.toDate();
  }

  public DateTime getLastModifiedDate() {
    return null == lastUpdated ? null : new DateTime(lastUpdated);
  }

  public void setLastModifiedDate(final DateTime lastModifiedDate) {
    this.lastUpdated = null == lastModifiedDate ? null : lastModifiedDate.toDate();
  }

  @Override
  public String getCreatedBy() {
    return createdBy;
  }

  @Override
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  @Override
  public String getLastModifiedBy() {
    return updatedBy;
  }

  @Override
  public void setLastModifiedBy(String lastModifiedBy) {
    this.updatedBy = lastModifiedBy;
  }
}
