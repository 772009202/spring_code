package com.chenyu.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author chenyu
 * @date 2021-02-04
 */
public class UriReSource implements Resource {

  private URL url;

  public UriReSource(URL url) {
    this.url = url;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    URLConnection urlConnection = url.openConnection();
    urlConnection.connect();
    return urlConnection.getInputStream();
  }
}
