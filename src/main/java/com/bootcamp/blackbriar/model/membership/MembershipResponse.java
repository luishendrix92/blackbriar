package com.bootcamp.blackbriar.model.membership;

import com.bootcamp.blackbriar.model.group.GroupResponse;
import com.bootcamp.blackbriar.model.user.UserRest;

public class MembershipResponse {
  private long id;
  private UserRest student;
  private GroupResponse group;
  private boolean active = true;
  private String statusMessage = "No message assigned...";

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public UserRest getStudent() {
    return student;
  }

  public void setStudent(UserRest member) {
    this.student = member;
  }

  public GroupResponse getGroup() {
    return group;
  }

  public void setGroup(GroupResponse group) {
    this.group = group;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }
}
