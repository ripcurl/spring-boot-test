package com.ium.test.rest.wsobjects;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for messages complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(name="messages")
@XmlType(name = "messages", propOrder = {
    "errors"
})
public class WsMessages implements Serializable {
  @XmlElementWrapper(name = "errors")
  @XmlElement(name = "error")
  protected List<WsErrorMessage> errors;

  public List<WsErrorMessage> getErrors() {
    return errors;
  }

  public void setErrors(List<WsErrorMessage> errors) {
    this.errors = errors;
  }
}
