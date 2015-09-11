package com.ium.test.rest.wsobjects;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Converter class for XSD to Java generation
 * When a type is created with xsd:date this converter
 * will generate a Java type of java.util.Date
 * @author luciano fiandesio
 */
public final class XsdDateTimeConverter {

  private XsdDateTimeConverter() {}

  /**
   * Un-marshal to java.util.Date
   * @param dateTime
   * @return
   */
  public static Date unmarshal(String dateTime) {
    return DatatypeConverter.parseDate(dateTime).getTime();
  }

  /**
   * Marshal date
   * @param date
   * @return
   */
  public static String marshalDate(Date date) {
    final GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    return DatatypeConverter.printDate(calendar);
  }

  /**
   * Marshal date-time
   * @param dateTime
   * @return
   */
  public static String marshalDateTime(Date dateTime) {
    final GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(dateTime);
    return DatatypeConverter.printDateTime(calendar);
  }

}
