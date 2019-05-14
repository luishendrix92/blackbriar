package com.bootcamp.blackbriar.model.group;

import com.bootcamp.blackbriar.model.user.UserRest;

public class GroupResponse {
  private long id;
  private String title;
  private String description;
  private String image = "blank.jpg";
  private UserRest owner;

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

  public UserRest getOwner() {
    return owner;
  }

  public void setOwner(UserRest owner) {
    this.owner = owner;
  }
}