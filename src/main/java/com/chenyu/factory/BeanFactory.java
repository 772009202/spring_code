package com.chenyu.factory;

/**
 * @author chenyu
 * @create 2021/2/3
 */
public interface BeanFactory {

  /**
   * 获取bean实例
   *
   * @param beanName
   * @return
   */
  Object getBean(String beanName) throws Exception;
}
