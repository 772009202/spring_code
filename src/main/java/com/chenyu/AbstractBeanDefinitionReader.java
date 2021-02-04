package com.chenyu;

import com.chenyu.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyu
 * @date 2021-02-04
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

  private Map<String, BeanDefinition> registry;

  private ResourceLoader resourceLoader;

  public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
    this.registry = new HashMap<>();
    this.resourceLoader = resourceLoader;
  }

  public Map<String, BeanDefinition> getRegistry() {
    return registry;
  }

  public ResourceLoader getResourceLoader() {
    return resourceLoader;
  }
}
