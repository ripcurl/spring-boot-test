package com.ium.test.rest.wsobjects;

import java.text.ParseException;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <b>Description:</b> This class is used by Jaxb to support multi implementations context
 */
public class JaxbAnyTypeDateAwareAdapter extends XmlAdapter<Object, Object> {

  @Override
  public Object marshal(Object value) {
    return value;
  }

  @Override
  public Object unmarshal(Object value) throws ParseException {
    if (value instanceof XMLGregorianCalendar){
      return ((XMLGregorianCalendar)value).toGregorianCalendar().getTime();
    }
    return value;
  }
}