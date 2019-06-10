package com.bootcamp.blackbriar.model.forum;

import java.util.Date;
import java.util.List;

import com.bootcamp.blackbriar.model.group.GroupEntity;

public class ForumResponse {
  private long id;
  private long groupId;
  private String title;
  private String description;
  private String content;
  private boolean published = false;
  private Date created;
  private ForumSettingsDetails settings;
  private List<ScoreRoleDetails> roles;

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
  
  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

  public long getGroupId() {
    return groupId;
  }

  public void setGroup(GroupEntity group) {
    this.groupId = group.getId();
  }

  public List<ScoreRoleDetails> getRoles() {
    return roles;
  }

  public void setRoles(List<ScoreRoleDetails> roles) {
    this.roles = roles;
  }
}
