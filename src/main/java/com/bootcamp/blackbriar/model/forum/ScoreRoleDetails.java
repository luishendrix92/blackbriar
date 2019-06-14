package com.bootcamp.blackbriar.model.forum;

import com.bootcamp.blackbriar.model.membership.MembershipEntity;

public class ScoreRoleDetails {
  private long groupMembershipId;
  private String firstName;
  private String lastName;
  private String photo;
  private boolean warrior;
  private boolean healer;
  private boolean warlock;
  private int score;

  public boolean isWarrior() {
    return warrior;
  }

  public void setWarrior(boolean warrior) {
    this.warrior = warrior;
  }

  public boolean isHealer() {
    return healer;
  }

  public void setHealer(boolean healer) {
    this.healer = healer;
  }

  public boolean isWarlock() {
    return warlock;
  }

  public void setWarlock(boolean warlock) {
    this.warlock = warlock;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public long getGroupMembershipId() {
    return groupMembershipId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPhoto() {
    return photo;
  }

  public void setMember(MembershipEntity forumGroupMember) {
    this.groupMembershipId = forumGroupMember.getId();
    this.firstName = forumGroupMember.getStudent().getFirstName();
    this.lastName = forumGroupMember.getStudent().getLastName();
    this.photo = forumGroupMember.getStudent().getPhoto();
  }
}
