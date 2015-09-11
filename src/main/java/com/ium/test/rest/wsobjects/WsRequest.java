package com.ium.test.rest.wsobjects;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for request complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(name="request")
@XmlType(name = "request")
public class WsRequest implements Serializable {
  @XmlElement(required = true)
  protected String method;
  @XmlElement(required = true)
  protected String path;
  @XmlElement(required = true)
  protected String url;
  protected String body;
  @XmlElementWrapper(name = "headers", required = true)
  @XmlElement(name = "entry")
  protected List<WsRequest.Entry> headers;
  @XmlElementWrapper(name = "parameters", required = true)
  @XmlElement(name = "entry")
  protected List<WsRequest.Entry> parameters;

  /**
   * Gets the value of the method property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getMethod() {
    return method;
  }

  /**
   * Sets the value of the method property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setMethod(String value) {
    this.method = value;
  }

  /**
   * Gets the value of the path property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getPath() {
    return path;
  }

  /**
   * Sets the value of the path property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPath(String value) {
    this.path = value;
  }

  /**
   * Gets the value of the url property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getUrl() {
    return url;
  }

  /**
   * Sets the value of the url property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setUrl(String value) {
    this.url = value;
  }

  /**
   * Gets the value of the body property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getBody() {
    return body;
  }

  /**
   * Sets the value of the body property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setBody(String value) {
    this.body = value;
  }

  public List<WsRequest.Entry> getHeaders() {
    return headers;
  }

  public void setHeaders(List<WsRequest.Entry> headers) {
    this.headers = headers;
  }

  public List<WsRequest.Entry> getParameters() {
    return parameters;
  }

  public void setParameters(List<WsRequest.Entry> parameters) {
    this.parameters = parameters;
  }


  /**
   * <p>Java class for anonymous complex type.
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "")
  public static class Entry implements Serializable {
    @XmlElement(required = true)
    protected String key;
    protected String value;

    /**
     * Gets the value of the key property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getKey() {
      return key;
    }

    /**
     * Sets the value of the key property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setKey(String value) {
      this.key = value;
    }

    /**
     * Gets the value of the value property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getValue() {
      return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setValue(String value) {
      this.value = value;
    }
  }
}
