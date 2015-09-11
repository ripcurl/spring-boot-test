package com.ium.test.rest.wsobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;


/**
 * <p>Java class for errorResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="errorResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="request" type="{http://com.kuoni.services.domain}request" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="messages" type="{http://com.kuoni.services.domain}messages" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(name="errorResponse")
@XmlType(name = "errorResponse")
public class WsErrorResponse implements Serializable {
  protected WsRequest request;
  protected int status;
  protected WsMessages messages;

  /**
   * Gets the value of the request property.
   *
   * @return
   *     possible object is
   *     {@link WsRequest }
   *
   */
  public WsRequest getRequest() {
    return request;
  }

  /**
   * Sets the value of the request property.
   *
   * @param value
   *     allowed object is
   *     {@link WsRequest }
   *
   */
  public void setRequest(WsRequest value) {
    this.request = value;
  }

  /**
   * Gets the value of the status property.
   *
   */
  public int getStatus() {
    return status;
  }

  /**
   * Sets the value of the status property.
   *
   */
  public void setStatus(int value) {
    this.status = value;
  }

  /**
   * Gets the value of the messages property.
   *
   * @return
   *     possible object is
   *     {@link WsMessages }
   *
   */
  public WsMessages getMessages() {
    return messages;
  }

  /**
   * Sets the value of the messages property.
   *
   * @param value
   *     allowed object is
   *     {@link WsMessages }
   *
   */
  public void setMessages(WsMessages value) {
    this.messages = value;
  }

}
