package com.bootcamp.blackbriar.model.group;

import java.util.List;
import java.util.stream.Collectors;

import com.bootcamp.blackbriar.model.membership.MembershipEntity;

public class InstructorGroupResponse {
  private long id;
  private int memberCount = 0;
  private String title;
  private String description;
  private String image;
  private boolean publicGroup = true;

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

  public int getMemberCount() {
    return memberCount;
  }

  public void setMembers(List<MembershipEntity> members) {
    this.memberCount = members.stream()
      .filter(member -> member.isActive())
      .collect(Collectors.counting())
      .intValue();
  }
}
