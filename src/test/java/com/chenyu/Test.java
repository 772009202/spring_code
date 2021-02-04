package com.chenyu;

import com.chenyu.context.ApplicationContext;
import com.chenyu.context.ClassPathXmlApplicationContext;
import com.chenyu.factory.AutowireCapableBeanFactory;
import com.chenyu.factory.BeanFactory;
import com.chenyu.io.ResourceLoader;
import com.chenyu.xml.XmlBeanDefinitonReader;

import java.util.Map;

/**
 * @author chenyu
 * @date 2021-02-04
 */
public class Test {

  /**
   * 第五版的测试
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    BeanFactory beanFactory = new AutowireCapableBeanFactory();
    XmlBeanDefinitonReader beanDefinitionReader = new XmlBeanDefinitonReader(new ResourceLoader());
    beanDefinitionReader.loadBeanDefinition("application.xml");
    Map<String, BeanDefinition> map = beanDefinitionReader.getRegistry();
  }

  /** 第六版的测试 */
  @org.junit.Test
  public void test6() throws Exception {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
    MessagesService messagesService =
        (MessagesService) applicationContext.getBean("helloWorldService");
    messagesService.println();
  }
}
