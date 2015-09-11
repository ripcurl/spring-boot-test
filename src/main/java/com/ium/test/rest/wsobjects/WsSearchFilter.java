package com.ium.test.rest.wsobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>Java class for searchFilter complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(name="searchFilter")
@XmlType(name = "searchFilter")
@XmlSeeAlso({
    String.class,
    Long.class,
    Boolean.class,
    BigDecimal.class,
    Date.class,
    ReferencedEntityTypes.class
})
public class WsSearchFilter implements Serializable {
  protected String name;
  @XmlJavaTypeAdapter(JaxbAnyTypeDateAwareAdapter.class)
  protected Object value;
  protected Comparison comparison;
  protected String group;
  protected MatchMode matchMode;

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
   * Gets the value of the value property.
   *
   * @return
   *     possible object is
   *     {@link Object }
   *
   */
  public Object getValue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   *
   * @param value
   *     allowed object is
   *     {@link Object }
   *
   */
  public void setValue(Object value) {
    this.value = value;
  }

  /**
   * Gets the value of the comparison property.
   *
   * @return
   *     possible object is
   *     {@link Comparison }
   *
   */
  public Comparison getComparison() {
    return comparison;
  }

  /**
   * Sets the value of the comparison property.
   *
   * @param value
   *     allowed object is
   *     {@link Comparison }
   *
   */
  public void setComparison(Comparison value) {
    this.comparison = value;
  }

  /**
   * Gets the value of the group property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getGroup() {
    return group;
  }

  /**
   * Sets the value of the group property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setGroup(String value) {
    this.group = value;
  }

  /**
   * Gets the value of the matchMode property.
   *
   * @return
   *     possible object is
   *     {@link MatchMode }
   *
   */
  public MatchMode getMatchMode() {
    return matchMode;
  }

  /**
   * Sets the value of the matchMode property.
   *
   * @param value
   *     allowed object is
   *     {@link MatchMode }
   *
   */
  public void setMatchMode(MatchMode value) {
    this.matchMode = value;
  }

}
