package com.ium.test.rest.wsobjects;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for referencedEntityTypes.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="referencedEntityTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="aggregationRule"/>
 *     &lt;enumeration value="allotment"/>
 *     &lt;enumeration value="characteristic"/>
 *     &lt;enumeration value="company"/>
 *     &lt;enumeration value="companyType"/>
 *     &lt;enumeration value="component"/>
 *     &lt;enumeration value="componentType"/>
 *     &lt;enumeration value="contentSpecific"/>
 *     &lt;enumeration value="contact"/>
 *     &lt;enumeration value="contract"/>
 *     &lt;enumeration value="contractComponent"/>
 *     &lt;enumeration value="contractRate"/>
 *     &lt;enumeration value="country"/>
 *     &lt;enumeration value="costEstimate"/>
 *     &lt;enumeration value="currency"/>
 *     &lt;enumeration value="exchangeRate"/>
 *     &lt;enumeration value="group"/>
 *     &lt;enumeration value="itinerary"/>
 *     &lt;enumeration value="itineraryType"/>
 *     &lt;enumeration value="title"/>
 *     &lt;enumeration value="lift"/>
 *     &lt;enumeration value="markup"/>
 *     &lt;enumeration value="placement"/>
 *     &lt;enumeration value="pricingCalculation"/>
 *     &lt;enumeration value="product"/>
 *     &lt;enumeration value="productType"/>
 *     &lt;enumeration value="reason"/>
 *     &lt;enumeration value="reservation"/>
 *     &lt;enumeration value="reservationComponent"/>
 *     &lt;enumeration value="role"/>
 *     &lt;enumeration value="tax"/>
 *     &lt;enumeration value="tourCategory"/>
 *     &lt;enumeration value="user"/>
 *     &lt;enumeration value="invoice"/>
 *     &lt;enumeration value="itineraryItem"/>
 *     &lt;enumeration value="itineraryComponent"/>
 *     &lt;enumeration value="city"/>
 *     &lt;enumeration value="resource"/>
 *     &lt;enumeration value="tour"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "referencedEntityTypes")
@XmlEnum
@javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(ReferencedEntityTypes.XmlAdapter.class)
public enum ReferencedEntityTypes {
  @XmlEnumValue("aggregationRule")
  AGGREGATION_RULE("aggregationRule"),
  @XmlEnumValue("allotment")
  ALLOTMENT("allotment"),
  @XmlEnumValue("characteristic")
  CHARACTERISTIC("characteristic"),
  @XmlEnumValue("company")
  COMPANY("company"),
  @XmlEnumValue("companyType")
  COMPANY_TYPE("companyType"),
  @XmlEnumValue("component")
  COMPONENT("component"),
  @XmlEnumValue("componentType")
  COMPONENT_TYPE("componentType"),
  @XmlEnumValue("contentSpecific")
  CONTENT_SPECIFIC("contentSpecific"),
  @XmlEnumValue("contact")
  CONTACT("contact"),
  @XmlEnumValue("contract")
  CONTRACT("contract"),
  @XmlEnumValue("contractComponent")
  CONTRACT_COMPONENT("contractComponent"),
  @XmlEnumValue("contractRate")
  CONTRACT_RATE("contractRate"),
  @XmlEnumValue("country")
  COUNTRY("country"),
  @XmlEnumValue("costEstimate")
  COST_ESTIMATE("costEstimate"),
  @XmlEnumValue("currency")
  CURRENCY("currency"),
  @XmlEnumValue("exchangeRate")
  EXCHANGE_RATE("exchangeRate"),
  @XmlEnumValue("group")
  GROUP("group"),
  @XmlEnumValue("itinerary")
  ITINERARY("itinerary"),
  @XmlEnumValue("itineraryType")
  ITINERARY_TYPE("itineraryType"),
  @XmlEnumValue("title")
  TITLE("title"),
  @XmlEnumValue("lift")
  LIFT("lift"),
  @XmlEnumValue("markup")
  MARKUP("markup"),
  @XmlEnumValue("placement")
  PLACEMENT("placement"),
  @XmlEnumValue("pricingCalculation")
  PRICING_CALCULATION("pricingCalculation"),
  @XmlEnumValue("product")
  PRODUCT("product"),
  @XmlEnumValue("productType")
  PRODUCT_TYPE("productType"),
  @XmlEnumValue("reason")
  REASON("reason"),
  @XmlEnumValue("reservation")
  RESERVATION("reservation"),
  @XmlEnumValue("reservationComponent")
  RESERVATION_COMPONENT("reservationComponent"),
  @XmlEnumValue("role")
  ROLE("role"),
  @XmlEnumValue("tax")
  TAX("tax"),
  @XmlEnumValue("tourCategory")
  TOUR_CATEGORY("tourCategory"),
  @XmlEnumValue("user")
  USER("user"),
  @XmlEnumValue("invoice")
  INVOICE("invoice"),
  @XmlEnumValue("itineraryItem")
  ITINERARY_ITEM("itineraryItem"),
  @XmlEnumValue("itineraryComponent")
  ITINERARY_COMPONENT("itineraryComponent"),
  @XmlEnumValue("city")
  CITY("city"),
  @XmlEnumValue("resource")
  RESOURCE("resource"),
  @XmlEnumValue("tour")
  TOUR("tour");
  private final String value;

  ReferencedEntityTypes(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static ReferencedEntityTypes fromValue(String v) {
    for (ReferencedEntityTypes c: ReferencedEntityTypes.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

  public static class XmlAdapter extends EnumXmlAdapter<ReferencedEntityTypes> {}
}
