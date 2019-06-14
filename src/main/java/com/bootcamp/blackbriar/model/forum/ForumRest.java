package com.bootcamp.blackbriar.model.forum;

import java.util.Date;

public class ForumRest {
  private long id;
  private String title;
  private String description;
  private boolean published = false;
  private Date created;
  private ForumSettingsDetails settings;

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

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
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
}
