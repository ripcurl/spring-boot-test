package com.ium.test.rest.wsobjects;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * This class replaces the default enum adaptor of JAXB just to make sure that invalid enum values
 * throw exception instead of swallowing the exception and setting the value to null.
 */
public abstract class EnumXmlAdapter<T extends Enum<T>> extends XmlAdapter<String, T> {
  protected final Class<T> targetClass;
  protected final Method valueMethod;
  protected final Method fromValueMethod;

  @SuppressWarnings("unchecked")
  protected EnumXmlAdapter() {
    targetClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
        .getActualTypeArguments()[0];
    try {
      valueMethod = targetClass.getDeclaredMethod("value");
      fromValueMethod = targetClass.getDeclaredMethod("fromValue", String.class);
    } catch (SecurityException | NoSuchMethodException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public T unmarshal(String v) throws Exception {
    return (T) fromValueMethod.invoke(null, v);
  }

  @Override
  public String marshal(T v) throws Exception {
    return (String) valueMethod.invoke(v);
  }

}
