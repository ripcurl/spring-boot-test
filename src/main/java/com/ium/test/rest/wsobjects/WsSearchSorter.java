package com.ium.test.rest.wsobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * <p>Java class for searchSorter complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(name="searchSorter")
@XmlType(name = "searchSorter")
public class WsSearchSorter implements Serializable {
  protected String name;
  protected SortOrder order;

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
   * Gets the value of the order property.
   *
   * @return
   *     possible object is
   *     {@link SortOrder }
   *
   */
  public SortOrder getOrder() {
    return order;
  }

  /**
   * Sets the value of the order property.
   *
   * @param value
   *     allowed object is
   *     {@link SortOrder }
   *
   */
  public void setOrder(SortOrder value) {
    this.order = value;
  }

}
