package com.bootcamp.blackbriar.model.inbox;

import java.util.Date;

public class MessageResponse {
  private long id;
  private String content;
  private String category;
  private boolean archived;
  private Long actionRef;
  private Date created;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public boolean isArchived() {
    return archived;
  }

  public void setArchived(boolean archived) {
    this.archived = archived;
  }

  public long getActionRef() {
    return actionRef;
  }

  public void setActionRef(Long actionRef) {
    this.actionRef = actionRef;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }
}
