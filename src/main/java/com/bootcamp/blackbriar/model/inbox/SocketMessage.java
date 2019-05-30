package com.bootcamp.blackbriar.model.inbox;

public class SocketMessage {
  private String category;
  private Long actionRef;
  private String content;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Long getActionRef() {
    return actionRef;
  }

  public void setActionRef(Long actionRef) {
    this.actionRef = actionRef;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
