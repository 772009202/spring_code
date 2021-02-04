package com.chenyu;

/**
 * @author chenyu
 * @create 2021/2/4
 */
public interface BeanDefinitionReader {

  /**
   * 加载bean
   *
   * @param location
   * @throws Exception
   */
  void loadBeanDefinition(String location) throws Exception;
}
