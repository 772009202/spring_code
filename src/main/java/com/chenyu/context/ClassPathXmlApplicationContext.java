package com.chenyu.context;

import com.chenyu.BeanDefinition;
import com.chenyu.factory.AbstractBeanFactory;
import com.chenyu.factory.AutowireCapableBeanFactory;
import com.chenyu.io.ResourceLoader;
import com.chenyu.xml.XmlBeanDefinitonReader;

import java.util.Map;

/**
 * 和spring 5.0 对比<br>
 * xmlbeandefinitionreader 在源码中其实也是作为属性存在的<br>
 * 还少aware、后置处理器比较关键的地方
 *
 * @author chenyu
 * @date 2021-02-04
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

  private String configLocation;

  public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory)
      throws Exception {
    super(beanFactory);
    this.configLocation = configLocation;
    refresh();
  }

  public ClassPathXmlApplicationContext(String location) throws Exception {
    this(location, new AutowireCapableBeanFactory());
  }

  @Override
  public void refresh() throws Exception {
    XmlBeanDefinitonReader xmlBeanDefinitionReader =
        new XmlBeanDefinitonReader(new ResourceLoader());
    xmlBeanDefinitionReader.loadBeanDefinition(configLocation);
    for (Map.Entry<String, BeanDefinition> beanDefinitionEntry :
        xmlBeanDefinitionReader.getRegistry().entrySet()) {
      beanFactory.registerDefintion(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
    }
  }
}
