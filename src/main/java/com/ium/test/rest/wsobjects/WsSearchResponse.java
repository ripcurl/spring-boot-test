package com.ium.test.rest.wsobjects;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for searchResponse complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(name="searchResponse")
@XmlType(name = "searchResponse")
public class WsSearchResponse implements Serializable {
  protected WsSearchRequest searchRequest;
  protected Integer total;
  @XmlElementWrapper(name = "items")
  @XmlElements({
      @XmlElement(name = "list", type = WsListSearchResult.class)
  })
  protected List<Serializable> items;

  /**
   * Gets the value of the searchRequest property.
   *
   * @return
   *     possible object is
   *     {@link WsSearchRequest }
   *
   */
  public WsSearchRequest getSearchRequest() {
    return searchRequest;
  }

  /**
   * Sets the value of the searchRequest property.
   *
   * @param value
   *     allowed object is
   *     {@link WsSearchRequest }
   *
   */
  public void setSearchRequest(WsSearchRequest value) {
    this.searchRequest = value;
  }

  /**
   * Gets the value of the total property.
   *
   * @return
   *     possible object is
   *     {@link Integer }
   *
   */
  public Integer getTotal() {
    return total;
  }

  /**
   * Sets the value of the total property.
   *
   * @param value
   *     allowed object is
   *     {@link Integer }
   *
   */
  public void setTotal(Integer value) {
    this.total = value;
  }

  public List<Serializable> getItems() {
    return items;
  }

  public void setItems(List<Serializable> items) {
    this.items = items;
  }

}
