package com.ium.test.rest.wsobjects;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * <p>Java class for searchRequest complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="searchRequest")
@XmlType(name = "searchRequest")
public class WsSearchRequest implements Serializable {
  protected Integer page;
  protected Integer limit;
  @XmlElement(defaultValue = "false")
  protected boolean countOnly;
  @XmlElementWrapper(name = "filters")
  @XmlElement(name = "filter")
  protected List<WsSearchFilter> filters;
  @XmlElementWrapper(name = "sorters")
  @XmlElement(name = "sorter")
  protected List<WsSearchSorter> sorters;

  /**
   * Gets the value of the page property.
   *
   * @return
   *     possible object is
   *     {@link Integer }
   *
   */
  public Integer getPage() {
    return page;
  }

  /**
   * Sets the value of the page property.
   *
   * @param value
   *     allowed object is
   *     {@link Integer }
   *
   */
  public void setPage(Integer value) {
    this.page = value;
  }

  /**
   * Gets the value of the limit property.
   *
   * @return
   *     possible object is
   *     {@link Integer }
   *
   */
  public Integer getLimit() {
    return limit;
  }

  /**
   * Sets the value of the limit property.
   *
   * @param value
   *     allowed object is
   *     {@link Integer }
   *
   */
  public void setLimit(Integer value) {
    this.limit = value;
  }

  /**
   * Gets the value of the countOnly property.
   *
   */
  public boolean isCountOnly() {
    return countOnly;
  }

  /**
   * Sets the value of the countOnly property.
   *
   */
  public void setCountOnly(boolean value) {
    this.countOnly = value;
  }

  public List<WsSearchFilter> getFilters() {
    return filters;
  }

  public void setFilters(List<WsSearchFilter> filters) {
    this.filters = filters;
  }

  public List<WsSearchSorter> getSorters() {
    return sorters;
  }

  public void setSorters(List<WsSearchSorter> sorters) {
    this.sorters = sorters;
  }
}
