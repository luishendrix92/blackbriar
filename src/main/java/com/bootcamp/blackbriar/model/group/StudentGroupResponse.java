package com.bootcamp.blackbriar.model.group;

import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.model.user.UserRest;

import org.springframework.beans.BeanUtils;

public class StudentGroupResponse {
  private long id;
  private String title;
  private String description;
  private String image;
  private UserRest owner;
  private boolean publicGroup = false;
  private boolean active = true;


  public StudentGroupResponse(long id, String title, String description, String image, UserEntity owner, boolean publicGroup, boolean active) {
    UserRest serializedOwner = new UserRest();

    BeanUtils.copyProperties(owner, serializedOwner);

    this.id = id;
    this.title = title;
    this.description = description;
    this.image = image;
    this.publicGroup = publicGroup;
    this.active = active;
    this.owner = serializedOwner;
  }


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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public boolean isPublicGroup() {
    return publicGroup;
  }

  public void setPublicGroup(boolean publicGroup) {
    this.publicGroup = publicGroup;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public UserRest getOwner() {
    return owner;
  }

  public void setOwner(UserRest owner) {
    this.owner = owner;
  }
}
