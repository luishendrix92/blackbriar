package com.bootcamp.blackbriar.model.group;

import com.bootcamp.blackbriar.model.membership.MembershipDetails;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;
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
  private MembershipDetails membership;


  public StudentGroupResponse(long id, String title, String description, String image, UserEntity owner, boolean publicGroup, MembershipEntity membership) {
    UserRest serializedOwner = new UserRest();
    MembershipDetails serializedMembership = null;

    BeanUtils.copyProperties(owner, serializedOwner);
    
    if (membership != null) {
      serializedMembership = new MembershipDetails();

      BeanUtils.copyProperties(membership, serializedMembership);
    }

    this.id = id;
    this.title = title;
    this.description = description;
    this.image = image;
    this.publicGroup = publicGroup;
    this.membership = serializedMembership;
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

  public UserRest getOwner() {
    return owner;
  }

  public void setOwner(UserRest owner) {
    this.owner = owner;
  }

  public MembershipDetails getMembership() {
    return membership;
  }

  public void setMembership(MembershipDetails membership) {
    this.membership = membership;
  }
}
