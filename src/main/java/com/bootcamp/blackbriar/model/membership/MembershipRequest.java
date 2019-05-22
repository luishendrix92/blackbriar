package com.bootcamp.blackbriar.model.membership;

public class MembershipRequest {
  private long groupId;
  private String userId;

  public long getGroupId() {
    return groupId;
  }

  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
