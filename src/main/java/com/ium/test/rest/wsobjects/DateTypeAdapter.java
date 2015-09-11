package com.ium.test.rest.wsobjects;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

public class DateTypeAdapter extends XmlAdapter<String, Date> {
  public Date unmarshal(String value) {
    return (XsdDateTimeConverter.unmarshal(value));
  }

  public String marshal(Date value) {
    return (XsdDateTimeConverter.marshalDateTime(value));
  }
}
