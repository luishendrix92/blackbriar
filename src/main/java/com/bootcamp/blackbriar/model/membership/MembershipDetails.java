package com.bootcamp.blackbriar.model.membership;

public class MembershipDetails {
  private boolean active = true;
  private boolean invitation = false;
  private long id;

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isInvitation() {
    return invitation;
  }

  public void setInvitation(boolean invitation) {
    this.invitation = invitation;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
