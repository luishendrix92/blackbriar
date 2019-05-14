package com.bootcamp.blackbriar.model.group;

import com.bootcamp.blackbriar.model.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name="Group")
@Table(name="grp")
public class GroupEntity implements Serializable {
  private static final long serialVersionUID = -7975243537766512485L;

  @Id
  @GeneratedValue
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_user")
  private UserEntity owner;

  @Column(length = 100, nullable = false)
  private String title;

  private String description;

  @Column(length = 100, nullable = false)
  private String image = "blank.jpg";

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public UserEntity getOwner() {
    return owner;
  }

  public void setOwner(UserEntity owner) {
    this.owner = owner;
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
}
