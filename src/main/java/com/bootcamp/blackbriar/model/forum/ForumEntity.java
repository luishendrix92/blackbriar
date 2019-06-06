package com.bootcamp.blackbriar.model.forum;

import com.bootcamp.blackbriar.model.group.GroupEntity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bootcamp.blackbriar.model.membershipForum.MembershipForumEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "Forum")
public class ForumEntity implements Serializable {
  private static final long serialVersionUID = 8942673069650119087L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 200)
  private String title;

  public List<MembershipForumEntity> getMembers() {
    return members;
  }

  public void setMembers(List<MembershipForumEntity> members) {
    this.members = members;
  }

  private String description;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private boolean visible = true;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;

  /**
   * Many to One Relationship: [Forum]<<-[Group]
   * ===========================================
   * A forum activity belongs to a group.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "fk_group")
  private GroupEntity group;

  /**
   * One to One Relationship: [Forum]->[ForumSettings]
   * =================================================
   * A forum activity has a set of settings and config
   * variables that can be changed by the group owner.
   */
  @OneToOne(
    fetch = FetchType.LAZY,
    mappedBy = "forum",
    cascade = CascadeType.ALL
  )
  private ForumSettingsEntity settings;

  /**One to Many Relationship: Forum -> membershipF **/
  @OneToMany(mappedBy = "forum")
  private List<MembershipForumEntity> members = new ArrayList<MembershipForumEntity>();

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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public GroupEntity getGroup() {
    return group;
  }

  public void setGroup(GroupEntity group) {
    this.group = group;
  }

  public ForumSettingsEntity getSettings() {
    return settings;
  }

  public void setSettings(ForumSettingsEntity settings) {
    this.settings = settings;
  }
}
