package com.ium.test.rest.wsobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for referencedUser complex type.
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@javax.xml.bind.annotation.XmlRootElement(name="referencedUser")
@XmlType(name = "referencedUser")
public class WsReferencedUser extends WsReferencedEntity {
  protected ReferencedEntityTypes type;

  /**
   * Gets the value of the type property.
   *
   * @return
   *     possible object is
   *     {@link ReferencedEntityTypes }
   *
   */
  @XmlAttribute(name = "type") public ReferencedEntityTypes getType() {
    if (type == null) {
      return ReferencedEntityTypes.USER;
    } else {
      return type;
    }
  }

  /**
   * Sets the value of the type property.
   *
   * @param value
   *     allowed object is
   *     {@link ReferencedEntityTypes }
   *
   */
  public void setType(ReferencedEntityTypes value) {
    this.type = value;
  }

}
