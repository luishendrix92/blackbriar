package com.bootcamp.blackbriar.model.membership;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.user.UserEntity;

@Entity(name = "Membership")
@Table(name = "membership")
public class MembershipEntity implements Serializable {
  private static final long serialVersionUID = 5L;

  @Id
  @GeneratedValue
  private long id;

  private boolean active = true;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_user")
  private UserEntity student;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_group")
  private GroupEntity group;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public UserEntity getStudent() {
    return student;
  }

  public void setStudent(UserEntity student) {
    this.student = student;
  }

  public GroupEntity getGroup() {
    return group;
  }

  public void setGroup(GroupEntity group) {
    this.group = group;
  }
}
