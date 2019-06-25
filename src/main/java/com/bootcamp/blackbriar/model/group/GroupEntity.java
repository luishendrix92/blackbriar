package com.bootcamp.blackbriar.model.group;

import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;
import com.bootcamp.blackbriar.model.user.UserEntity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(name="Group")
@Table(name="grp")
public class GroupEntity implements Serializable {
  private static final long serialVersionUID = -7975243537766512485L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_user")
  private UserEntity owner;

  /**
   * One to Many Relationship: [Group]->>[Forum]
   * ===========================================
   * Study groups can have one or more forum
   * activities listed and never shared.
   */
  @OneToMany(mappedBy = "group")
  private List<ForumEntity> forums = new ArrayList<ForumEntity>();

  /**
   * Many to Many Relationship: [Group]<<->>[User]
   * =============================================
   * A study group has a list of users that have
   * decided to join it. This relationship goes
   * through an extra entity called Membership.
   */
  @OneToMany(mappedBy = "group")
  private List<MembershipEntity> members = new ArrayList<MembershipEntity>();

  @Column(length = 100, nullable = false)
  private String title;

  @Lob
  @Type(type = "text")
  @Column(nullable = false)
  private String description;

  @Column(length = 100, nullable = false)
  private String image = "https://blackbriarfiles.s3.amazonaws.com/default.png";

  private boolean publicGroup = true;

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

  public boolean isPublicGroup() {
    return publicGroup;
  }

  public void setPublicGroup(boolean publicGroup) {
    this.publicGroup = publicGroup;
  }

  public List<MembershipEntity> getMembers() {
    return members;
  }

  public void setMembers(List<MembershipEntity> members) {
    this.members = members;
  }
}
