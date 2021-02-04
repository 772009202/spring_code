package com.chenyu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyu
 * @date 2021-02-03
 */
public class PropertyValues {

  private List<PropertyValue> propertyValueList = new ArrayList<>();

  public void addPropertyValue(PropertyValue value) {
    this.propertyValueList.add(value);
  }

  public List<PropertyValue> getList() {
    return propertyValueList;
  }

  public void setList(List<PropertyValue> propertyValueList) {
    this.propertyValueList = propertyValueList;
  }
}
