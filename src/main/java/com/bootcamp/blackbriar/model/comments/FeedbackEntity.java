package com.bootcamp.blackbriar.model.comments;

import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "Feedback")
public class FeedbackEntity implements Serializable {
  private static final long serialVersionUID = 8942673069650119087L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private boolean approved = false;

  @Column(nullable = false)
  @Lob
  @Type(type = "text")
  private String content;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "fk_answer")
  private AnswerEntity parent;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "fk_student")
  private FMembershipEntity student;

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "fk_instructor")
  private UserEntity instructor;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public AnswerEntity getParent() {
    return parent;
  }

  public void setParent(AnswerEntity parent) {
    this.parent = parent;
  }

  public FMembershipEntity getStudent() {
    return student;
  }

  public void setStudent(FMembershipEntity student) {
    this.student = student;
  }

  public UserEntity getInstructor() {
    return instructor;
  }

  public void setInstructor(UserEntity instructor) {
    this.instructor = instructor;
  }

  public boolean isApproved() {
    return approved;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }
}
