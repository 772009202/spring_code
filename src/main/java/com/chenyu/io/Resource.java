package com.chenyu.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenyu
 * @date 2021-02-04
 */
public interface Resource {

  InputStream getInputStream() throws IOException;
}
