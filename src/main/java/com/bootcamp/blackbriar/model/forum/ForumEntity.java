package com.bootcamp.blackbriar.model.forum;

import com.bootcamp.blackbriar.model.comments.AnswerEntity;
import com.bootcamp.blackbriar.model.group.GroupEntity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Type;

@Entity(name = "Forum")
public class ForumEntity implements Serializable {
  private static final long serialVersionUID = 8942673069650119087L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 200)
  private String title;

  private String description;

  @Column(nullable = false)
  @Lob
  @Type(type = "text")
  private String content;

  @Column(nullable = false)
  private boolean published = false;

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

  /**
   * One to Many Relationship: [Forum]->>[ForumMembership]
   * =====================================================
   * Forums automatically assign roles and an initial
   * score to all active group members. This state is
   * kept in an entity called ForumMembership.
   */
  @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL)
  // @OrderBy(value = "score")
  private List<FMembershipEntity> scoreboard = new ArrayList<FMembershipEntity>();

  /** 
   * One to Many Relationship: [Forum]->>[Answer]
   * ============================================
   * A forum may have answers (comments) made by
   * students with or without replies.
   */
  @OneToMany(mappedBy = "forum", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<AnswerEntity> answers = new ArrayList<AnswerEntity>();

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

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

  public List<FMembershipEntity> getScoreboard() {
    return scoreboard;
  }

  public void setScoreboard(List<FMembershipEntity> scoreboard) {
    this.scoreboard = scoreboard;
  }

  public List<AnswerEntity> getAnswers() {
    return answers;
  }

  public void setAnswers(List<AnswerEntity> answers) {
    this.answers = answers;
  }
}
