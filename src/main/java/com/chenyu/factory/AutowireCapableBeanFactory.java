package com.chenyu.factory;

import com.chenyu.BeanDefinition;
import com.chenyu.BeanReference;
import com.chenyu.PropertyValue;

import java.lang.reflect.Field;

/**
 * @author chenyu
 * @date 2021-02-03
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

  protected Object creatBean(BeanDefinition beanDefinition) throws Exception {
    Object bean = doCreatBean(beanDefinition);
    setValueByPropertyValues(bean, beanDefinition);
    return bean;
  }

  @Override
  protected Object doCreatBean(BeanDefinition beanDefinition) throws Exception {
    Object bean = null;
    try {
      /** 这里先直接通过无参构造函数构建bean */
      bean = beanDefinition.getBeanClass().newInstance();
      /** 即使有循环依赖也没关系 因为被循环依赖的bean 已经可以找到之前实例化的bean了 */
      setValueByPropertyValues(bean, beanDefinition);
      return bean;
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    if (bean == null) {
      throw new Exception("不能实例化");
    }
    return bean;
  }

  private void setValueByPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
    for (PropertyValue e : mbd.getPropertyValues().getList()) {
      Field field = bean.getClass().getDeclaredField(e.getKey());
      Object value = e.getValue();
      field.setAccessible(true);
      if (value instanceof BeanReference) {
        BeanReference beanReference = (BeanReference) value;
        value = getBean(beanReference.getName());
      }
      field.set(bean, value);
    }
  }
}
