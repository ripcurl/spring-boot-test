package com.ium.test.rest.wsobjects;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>Java class for sortOrder.
 */
@XmlType(name = "sortOrder")
@XmlEnum
@XmlJavaTypeAdapter(SortOrder.XmlAdapter.class)
public enum SortOrder {
  ASCENDING,
  DESCENDING;

  public String value() {
    return name();
  }

  public static SortOrder fromValue(String v) {
    return valueOf(v);
  }


  public static class XmlAdapter extends EnumXmlAdapter<SortOrder> {}
}
