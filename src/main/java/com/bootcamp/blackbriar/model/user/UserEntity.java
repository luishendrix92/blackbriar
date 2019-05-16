package com.bootcamp.blackbriar.model.user;

import com.bootcamp.blackbriar.model.group.GroupEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Table(name = "usr")
public class UserEntity implements Serializable {
  private static final long serialVersionUID = -8011797215999645996L;

  @Id
  @GeneratedValue
  private long id;

  @OneToMany(mappedBy = "owner")
  private List<GroupEntity> ownedGroups = new ArrayList<GroupEntity>();

  @Column(nullable = false)
  private String userId;

  @Column(nullable = false, length = 100)
  private String email;

  @Column(nullable = false, length = 50)
  private String firstName;

  @Column(nullable = false, length = 50)
  private String lastName;

  @Column(length = 100)
  private String photo = "anon.jpg";

  @Column(nullable = false)
  private String encryptedPassword;

  private String emailVerificationToken;

  @Column(nullable = false)
  private boolean emailVerificationStatus = false;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<GroupEntity> getOwnedGroups() {
    return ownedGroups;
  }

  public void setOwnedGroups(List<GroupEntity> ownedGroups) {
    this.ownedGroups = ownedGroups;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public boolean isEmailVerificationStatus() {
    return emailVerificationStatus;
  }

  public void setEmailVerificationStatus(boolean emailVerificationStatus) {
    this.emailVerificationStatus = emailVerificationStatus;
  }
}
