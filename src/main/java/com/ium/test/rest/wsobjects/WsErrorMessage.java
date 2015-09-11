package com.ium.test.rest.wsobjects;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;

/**
 * <p>Java class for errorMessage complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(name="errorMessage")
@XmlType(name = "errorMessage")
public class WsErrorMessage implements Serializable {
  protected String code;
  protected String message;
  protected String expected;
  protected String stacktrace;

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
   * Gets the value of the message property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the value of the message property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setMessage(String value) {
    this.message = value;
  }

  /**
   * Gets the value of the expected property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getExpected() {
    return expected;
  }

  /**
   * Sets the value of the expected property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setExpected(String value) {
    this.expected = value;
  }

  /**
   * Gets the value of the stacktrace property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getStacktrace() {
    return stacktrace;
  }

  /**
   * Sets the value of the stacktrace property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setStacktrace(String value) {
    this.stacktrace = value;
  }

}

