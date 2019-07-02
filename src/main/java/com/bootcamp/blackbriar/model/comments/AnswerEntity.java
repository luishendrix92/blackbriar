package com.bootcamp.blackbriar.model.comments;

import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.forum.ForumEntity;
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

@Entity(name = "Answer")
public class AnswerEntity implements Serializable {
  private static final long serialVersionUID = 8942673069650119081L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = true)
  private Boolean approved;

  @Column(nullable = false)
  private int attempts = 1;

  @Column(nullable = false)
  @Lob
  @Type(type = "text")
  private String content;

  @Column(nullable = true, length = 2000)
  private String files;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
  private List<FeedbackEntity> replies = new ArrayList<FeedbackEntity>();

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "fk_forum")
  private ForumEntity forum;

  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "fk_student")
  private FMembershipEntity student;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Boolean getApproved() {
    return approved;
  }

  public void setApproved(Boolean approved) {
    this.approved = approved;
  }

  public int getAttempts() {
    return attempts;
  }

  public void setAttempts(int attempts) {
    this.attempts = attempts;
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

  public List<FeedbackEntity> getReplies() {
    return replies;
  }

  public void setReplies(List<FeedbackEntity> replies) {
    this.replies = replies;
  }

  public ForumEntity getForum() {
    return forum;
  }

  public void setForum(ForumEntity forum) {
    this.forum = forum;
  }

  public FMembershipEntity getStudent() {
    return student;
  }

  public void setStudent(FMembershipEntity student) {
    this.student = student;
  }

  public String getFiles() {
    return files;
  }

  public void setFiles(String files) {
    this.files = files;
  }
}
