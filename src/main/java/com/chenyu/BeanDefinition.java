package com.chenyu;

/**
 * 用来定义bean的信息
 *
 * @author chenyu
 * @date 2021-02-03
 */
public class BeanDefinition {

  private Object bean;

  private Class beanClass;

  private String beanClassName;

  private PropertyValues propertyValues = new PropertyValues();

  public Object getBean() {
    return bean;
  }

  public void setBean(Object bean) {
    this.bean = bean;
  }

  public Class getBeanClass() {
    return beanClass;
  }

  public void setBeanClass(Class beanClass) {
    this.beanClass = beanClass;
  }

  public String getBeanClassName() {
    return beanClassName;
  }

  public void setBeanClassName(String beanClassName) {
    try {
      this.beanClass = Class.forName(beanClassName);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    this.beanClassName = beanClassName;
  }

  public PropertyValues getPropertyValues() {
    return propertyValues;
  }

  public void setPropertyValues(PropertyValues propertyValues) {
    this.propertyValues = propertyValues;
  }
}
