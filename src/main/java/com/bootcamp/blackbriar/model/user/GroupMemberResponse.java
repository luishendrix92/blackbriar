package com.bootcamp.blackbriar.model.user;

import com.bootcamp.blackbriar.model.membership.MembershipDetails;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;

import org.springframework.beans.BeanUtils;

/**
 * GroupMemberResponse
 */
public class GroupMemberResponse {
  private String firstName;
  private String lastName;
  private String email;
  private String userId;
  private String photo;
  private MembershipDetails membership;

  public GroupMemberResponse(String firstName, String lastName, String email, String userId, String photo, MembershipEntity membership) {
    MembershipDetails serializedMembership = new MembershipDetails();

    BeanUtils.copyProperties(membership, serializedMembership);

    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.userId = userId;
    this.photo = photo;
    this.membership = serializedMembership;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public MembershipDetails getMembership() {
    return membership;
  }

  public void setMembership(MembershipDetails membership) {
    this.membership = membership;
  }
}
