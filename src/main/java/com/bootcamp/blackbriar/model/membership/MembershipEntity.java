package com.bootcamp.blackbriar.model.membership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.membershipForum.MembershipForumEntity;
import com.bootcamp.blackbriar.model.user.UserEntity;

@Entity(name = "Membership")
@Table(name = "membership")
public class MembershipEntity implements Serializable {
  private static final long serialVersionUID = 5L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private boolean active = true;
  private boolean invitation = false;

  public List<MembershipForumEntity> getGroupForums() {
    return groupForums;
  }

  public void setGroupForums(List<MembershipForumEntity> groupForums) {
    this.groupForums = groupForums;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_user")
  private UserEntity student;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_group")
  private GroupEntity group;

  @OneToMany(mappedBy = "memberGroup")
  private List<MembershipForumEntity> groupForums = new ArrayList<MembershipForumEntity>();

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

  public boolean isInvitation() {
    return invitation;
  }

  public void setInvitation(boolean invitation) {
    this.invitation = invitation;
  }
}
