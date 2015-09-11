package com.ium.test.rest.wsobjects;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Java class for listSearchResult complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="listSearchResult")
@XmlType(name = "listSearchResult")
public class WsListSearchResult implements Serializable {
  protected String id;
  protected String name;
  protected String code;
  protected String ownerId;
  protected String ownerName;
  protected String ownerCode;
  protected String ownerType;
  @XmlElement(type = String.class)
  @XmlJavaTypeAdapter(DateTypeAdapter.class)
  @XmlSchemaType(name = "dateTime")
  protected Date lastUpdateDate;
  protected String lastUpdateUserId;
  protected String lastUpdateUserName;

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
   * Gets the value of the ownerId property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getOwnerId() {
    return ownerId;
  }

  /**
   * Sets the value of the ownerId property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setOwnerId(String value) {
    this.ownerId = value;
  }

  /**
   * Gets the value of the ownerName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getOwnerName() {
    return ownerName;
  }

  /**
   * Sets the value of the ownerName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setOwnerName(String value) {
    this.ownerName = value;
  }

  /**
   * Gets the value of the ownerCode property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getOwnerCode() {
    return ownerCode;
  }

  /**
   * Sets the value of the ownerCode property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setOwnerCode(String value) {
    this.ownerCode = value;
  }

  /**
   * Gets the value of the ownerType property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getOwnerType() {
    return ownerType;
  }

  /**
   * Sets the value of the ownerType property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setOwnerType(String value) {
    this.ownerType = value;
  }

  /**
   * Gets the value of the lastUpdateDate property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public Date getLastUpdateDate() {
    return lastUpdateDate;
  }

  /**
   * Sets the value of the lastUpdateDate property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setLastUpdateDate(Date value) {
    this.lastUpdateDate = value;
  }

  /**
   * Gets the value of the lastUpdateUserId property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getLastUpdateUserId() {
    return lastUpdateUserId;
  }

  /**
   * Sets the value of the lastUpdateUserId property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setLastUpdateUserId(String value) {
    this.lastUpdateUserId = value;
  }

  /**
   * Gets the value of the lastUpdateUserName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getLastUpdateUserName() {
    return lastUpdateUserName;
  }

  /**
   * Sets the value of the lastUpdateUserName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setLastUpdateUserName(String value) {
    this.lastUpdateUserName = value;
  }

}
