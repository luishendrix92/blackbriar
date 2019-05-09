package com.bootcamp.blackbriar.model;

public class UserLoginRest {
  private String userId;
  private String firstName;
  private String lastName;
  private String photo;
  private String token;
  private long tokenExpiration;

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

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public long getTokenExpiration() {
    return tokenExpiration;
  }

  public void setTokenExpiration(long tokenExpiration) {
    this.tokenExpiration = tokenExpiration;
  }
}
