package com.chenyu;

/**
 * @author chenyu
 * @date 2021-02-03
 */
public class MessagesService {
  private String messages;

  public String getMessages() {
    return messages;
  }

  public void setMessages(String messages) {
    this.messages = messages;
  }

  public void println() {
    System.out.println(messages);
  }
}
