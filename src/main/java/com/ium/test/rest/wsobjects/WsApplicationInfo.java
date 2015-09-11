package com.ium.test.rest.wsobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;

/**
 * <p>Java class for applicationInfo complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(name="applicationInfo")
@XmlType(name = "applicationInfo")
public class WsApplicationInfo implements Serializable {
  protected String version;

  /**
   * Gets the value of the version property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getVersion() {
    return version;
  }

  /**
   * Sets the value of the version property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setVersion(String value) {
    this.version = value;
  }

}
