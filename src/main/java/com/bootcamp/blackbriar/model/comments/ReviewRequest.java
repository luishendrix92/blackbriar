package com.bootcamp.blackbriar.model.comments;

public class ReviewRequest {
  private boolean approved = true;
  private String reason;

  public boolean isApproved() {
    return approved;
  }

  public void setApproved(boolean approve) {
    this.approved = approve;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
