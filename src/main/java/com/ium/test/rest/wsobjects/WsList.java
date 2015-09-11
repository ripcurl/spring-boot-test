package com.ium.test.rest.wsobjects;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for list complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(name="list")
@XmlType(name = "list")
public class WsList implements Serializable {
  protected String id;
  protected String name;
  protected String code;
  protected Long version;
  protected WsUserAndDate created;
  protected WsUserAndDate lastUpdate;
  protected WsReferenced owner;
  @XmlElementWrapper(name = "entries")
  @XmlElement(name = "entry")
  protected List<WsReferenced> entries;

  /**
   * Gets the value of the id property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setId(String value) {
    this.id = value;
  }

  /**
   * Gets the value of the name property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the name property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setName(String value) {
    this.name = value;
  }

  /**
   * Gets the value of the code property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getCode() {
    return code;
  }

  /**
   * Sets the value of the code property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setCode(String value) {
    this.code = value;
  }

  /**
   * Gets the value of the version property.
   *
   * @return
   *     possible object is
   *     {@link Long }
   *
   */
  public Long getVersion() {
    return version;
  }

  /**
   * Sets the value of the version property.
   *
   * @param value
   *     allowed object is
   *     {@link Long }
   *
   */
  public void setVersion(Long value) {
    this.version = value;
  }

  /**
   * Gets the value of the created property.
   *
   * @return
   *     possible object is
   *     {@link WsUserAndDate }
   *
   */
  public WsUserAndDate getCreated() {
    return created;
  }

  /**
   * Sets the value of the created property.
   *
   * @param value
   *     allowed object is
   *     {@link WsUserAndDate }
   *
   */
  public void setCreated(WsUserAndDate value) {
    this.created = value;
  }

  /**
   * Gets the value of the lastUpdate property.
   *
   * @return
   *     possible object is
   *     {@link WsUserAndDate }
   *
   */
  public WsUserAndDate getLastUpdate() {
    return lastUpdate;
  }

  /**
   * Sets the value of the lastUpdate property.
   *
   * @param value
   *     allowed object is
   *     {@link WsUserAndDate }
   *
   */
  public void setLastUpdate(WsUserAndDate value) {
    this.lastUpdate = value;
  }

  /**
   * Gets the value of the owner property.
   *
   * @return
   *     possible object is
   *     {@link WsReferenced }
   *
   */
  public WsReferenced getOwner() {
    return owner;
  }

  /**
   * Sets the value of the owner property.
   *
   * @param value
   *     allowed object is
   *     {@link WsReferenced }
   *
   */
  public void setOwner(WsReferenced value) {
    this.owner = value;
  }

  public List<WsReferenced> getEntries() {
    return entries;
  }

  public void setEntries(List<WsReferenced> entries) {
    this.entries = entries;
  }

}
