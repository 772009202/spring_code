package com.chenyu.io;

import java.net.URL;

/**
 * 资源加载器
 *
 * @author chenyu
 * @date 2021-02-04
 */
public class ResourceLoader {

  public Resource getResource(String location) {
    URL resource = this.getClass().getClassLoader().getResource(location);
    return new UriReSource(resource);
  }
}
