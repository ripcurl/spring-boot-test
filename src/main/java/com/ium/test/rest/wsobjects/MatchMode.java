package com.ium.test.rest.wsobjects;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>Java class for matchMode.
 */
@XmlType(name = "matchMode")
@XmlEnum
@XmlJavaTypeAdapter(MatchMode.XmlAdapter.class)
public enum MatchMode {
  STRICT,
  NON_STRICT;

  public String value() {
    return name();
  }

  public static MatchMode fromValue(String v) {
    return valueOf(v);
  }

  public static class XmlAdapter extends EnumXmlAdapter<MatchMode> {}
}
