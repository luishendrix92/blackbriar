package com.bootcamp.blackbriar.model.forum;

import java.util.Date;

import com.bootcamp.blackbriar.model.group.InstructorGroupResponse;

public class ForumResponse {
  private long id;
  private String title;
  private String description;
  private String content;
  private boolean visible = true;
  private Date created;
  private ForumSettingsDetails settings;
  private InstructorGroupResponse group;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public ForumSettingsDetails getSettings() {
    return settings;
  }

  public void setSettings(ForumSettingsDetails settings) {
    this.settings = settings;
  }

  public InstructorGroupResponse getGroup() {
    return group;
  }

  public void setGroup(InstructorGroupResponse group) {
    this.group = group;
  }
}
