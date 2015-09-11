package com.ium.test.rest.wsobjects;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>Java class for userAndDate complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(name="userAndDate")
@XmlType(name = "userAndDate")
public class WsUserAndDate implements Serializable {
  @XmlElement(required = true, type = String.class)
  @XmlJavaTypeAdapter(DateTypeAdapter.class)
  @XmlSchemaType(name = "dateTime")
  protected Date date;
  @XmlElement(required = true)
  protected WsReferencedUser user;

  /**
   * Gets the value of the date property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public Date getDate() {
    return date;
  }

  /**
   * Sets the value of the date property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setDate(Date value) {
    this.date = value;
  }

  /**
   * Gets the value of the user property.
   *
   * @return
   *     possible object is
   *     {@link WsReferencedUser }
   *
   */
  public WsReferencedUser getUser() {
    return user;
  }

  /**
   * Sets the value of the user property.
   *
   * @param value
   *     allowed object is
   *     {@link WsReferencedUser }
   *
   */
  public void setUser(WsReferencedUser value) {
    this.user = value;
  }
}
