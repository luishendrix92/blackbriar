package com.bootcamp.blackbriar.model.membership;

public class MembershipDetails {
  private boolean active = true;
  private boolean invitation = false;

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
}
