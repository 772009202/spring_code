package com.chenyu.context;

import com.chenyu.factory.AbstractBeanFactory;

/**
 * @author chenyu
 * @date 2021-02-04
 */
public class AbstractApplicationContext implements ApplicationContext {
  protected AbstractBeanFactory beanFactory;

  public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }

  @Override
  public Object getBean(String beanName) throws Exception {
    return beanFactory.getBean(beanName);
  }

  public void refresh() throws Exception {}
}
