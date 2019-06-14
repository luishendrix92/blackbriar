package com.bootcamp.blackbriar.model.feedback;

import com.bootcamp.blackbriar.model.answer.AnswerEntity;
import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "Feedback")
public class FeedbackEntity implements Serializable {
  private static final long serialVersionUID = 8942673069650119087L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  @Lob
  private String content;


  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "fk_answer")
  private AnswerEntity answer;

  @ManyToOne(fetch = FetchType.LAZY,
  optional = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "fk_student")
  private FMembershipEntity student;

  @ManyToOne(fetch = FetchType.LAZY,
  optional = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "fk_owner")
  private UserEntity owner;



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

    public AnswerEntity getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerEntity answer) {
        this.answer = answer;
    }

    public FMembershipEntity getStudent() {
        return student;
    }

    public void setStudent(FMembershipEntity student) {
        this.student = student;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

  
}
