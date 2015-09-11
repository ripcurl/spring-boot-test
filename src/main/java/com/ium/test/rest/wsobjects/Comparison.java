package com.ium.test.rest.wsobjects;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>Java class for comparison.
 */
@XmlType(name = "comparison")
@XmlEnum
@XmlJavaTypeAdapter(Comparison.XmlAdapter.class)
public enum Comparison {
  EQUALS,
  NOT_EQUALS,
  GREATER_THAN_OR_EQUALS,
  LESSER_THAN_OR_EQUALS,
  GREATER_THAN,
  LESSER_THAN,
  LIKE,
  ILIKE,
  START_LIKE,
  END_LIKE,
  START_ILIKE,
  END_ILIKE;

  public String value() {
    return name();
  }

  public static Comparison fromValue(String v) {
    return valueOf(v);
  }

  public static class XmlAdapter extends EnumXmlAdapter<Comparison> {}
}
