package com.chenyu.factory;

import com.chenyu.BeanDefinition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用来存储bean的信息
 *
 * @author chenyu
 * @date 2021-02-03
 */
public abstract class AbstractBeanFactory implements BeanFactory {

  private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap();

  private List<String> beanDefinitionNames = new ArrayList<>();

  @Override
  public Object getBean(String beanName) throws Exception {
    BeanDefinition bd = this.beanDefinitionMap.get(beanName);
    if (bd == null) {
      throw new IllegalArgumentException("no bean named " + beanName + "is defined");
    }
    Object bean = bd.getBean();
    if (bean == null) {
      bean = doCreatBean(bd);
    }
    return bean;
  }

  public void registerDefintion(String beanName, BeanDefinition beanDefinition) {
    this.beanDefinitionMap.put(beanName, beanDefinition);
    beanDefinitionNames.add(beanName);
  }

  /**
   * 提前创建单例
   *
   * @throws Exception
   */
  public void preInstantiateSingletons() throws Exception {
    for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext(); ) {
      String beanName = (String) it.next();
      getBean(beanName);
    }
  }

  /**
   * 模板模式 交给子类实现
   *
   * @param beanDefinition
   * @return
   */
  protected abstract Object doCreatBean(BeanDefinition beanDefinition) throws Exception;
}
