package com.chenyu.xml;

import com.chenyu.AbstractBeanDefinitionReader;
import com.chenyu.BeanDefinition;
import com.chenyu.BeanReference;
import com.chenyu.PropertyValue;
import com.chenyu.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenyu
 * @create 2021/2/4
 */
public class XmlBeanDefinitonReader extends AbstractBeanDefinitionReader {
  public XmlBeanDefinitonReader(ResourceLoader resourceLoader) {
    super(resourceLoader);
  }

  @Override
  public void loadBeanDefinition(String location) throws Exception {
    InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
    doLoadBeanDefinitions(inputStream);
  }

  protected void doLoadBeanDefinitions(InputStream inputStream)
      throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = factory.newDocumentBuilder();
    Document doc = documentBuilder.parse(inputStream);
    // 解析注册
    registerBeanDefinitions(doc);
    inputStream.close();
  }

  public void registerBeanDefinitions(Document doc) {
    Element root = doc.getDocumentElement();

    parseBeanDefinitions(root);
  }

  protected void parseBeanDefinitions(Element root) {
    NodeList childNodes = root.getChildNodes();
    for (int i = 0; i < childNodes.getLength(); i++) {
      Node node = childNodes.item(i);
      if (node instanceof Element) {
        Element ele = (Element) node;
        processBeanDefinition(ele);
      }
    }
  }

  protected void processBeanDefinition(Element ele) {
    String name = ele.getAttribute("name");
    String className = ele.getAttribute("class");
    BeanDefinition beanDefinition = new BeanDefinition();
    processProperty(ele, beanDefinition);
    beanDefinition.setBeanClassName(className);
    getRegistry().put(name, beanDefinition);
  }

  private void processProperty(Element ele, BeanDefinition beanDefinition) {
    NodeList propertyNode = ele.getElementsByTagName("property");
    for (int i = 0; i < propertyNode.getLength(); i++) {
      Node node = propertyNode.item(i);
      if (node instanceof Element) {
        Element pele = (Element) node;
        String name = pele.getAttribute("name");
        String value = pele.getAttribute("value");
        if (value != null && value.length() > 0) {
          beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
        } else {
          String ref = pele.getAttribute("ref");
          if (ref == null || ref.length() == 0) {
            throw new IllegalArgumentException(
                "Configuration problem: <property> element for property '"
                    + name
                    + "' must specify a ref or value");
          }
          BeanReference beanReference = new BeanReference(ref);
          beanDefinition
              .getPropertyValues()
              .addPropertyValue(new PropertyValue(name, beanReference));
        }
      }
    }
  }
}
