package com.bootcamp.blackbriar.model.user;

public class UserProfile {
  private String userId;
  private String firstName;
  private String lastName;
  private String email;
  private String photo;
  private boolean isStudent = true;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public boolean isStudent() {
    return isStudent;
  }

  public void setStudent(boolean isStudent) {
    this.isStudent = isStudent;
  }
}
