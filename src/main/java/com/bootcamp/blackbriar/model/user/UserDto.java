package com.bootcamp.blackbriar.model.user;

import java.io.Serializable;

public class UserDto implements Serializable {
  private static final long serialVersionUID = -6546925859137218064L;

  private long id;
  private String userId;
  private String encryptedPassword;
  private String emailVerificationToken;
  private boolean emailVerificationStatus = false;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private boolean isStudent = true;

  private String photo = "anon.jpg";

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getEncryptedPassword() {
    return encryptedPassword;
  }

  public void setEncryptedPassword(String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
  }

  public String getEmailVerificationToken() {
    return emailVerificationToken;
  }

  public void setEmailVerificationToken(String emailVerificationToken) {
    this.emailVerificationToken = emailVerificationToken;
  }

  public boolean getEmailVerificationStatus() {
    return emailVerificationStatus;
  }

  public void setEmailVerificationStatus(boolean emailVerificationStatus) {
    this.emailVerificationStatus = emailVerificationStatus;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
