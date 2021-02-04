package com.chenyu;

/**
 * @author chenyu
 * @date 2021-02-03
 */
public class PropertyValue {

  private String key;

  private Object value;

  public PropertyValue(String key, Object value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }
}
