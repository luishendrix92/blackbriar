package com.bootcamp.blackbriar.model.answer;

import com.bootcamp.blackbriar.model.feedback.FeedbackEntity;
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
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "Answer")
public class AnswerEntity implements Serializable {
  private static final long serialVersionUID = 8942673069650119087L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;


  @Column(nullable = true)
  private Boolean validate;

  @Column(nullable = false)
  private int counter = 1;

  @Column(nullable = false)
  @Lob
  private String content;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;


  @OneToMany(mappedBy = "answer")
  private List<FeedbackEntity> feedbacks = new ArrayList<FeedbackEntity>();

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "fk_forum")
  private ForumEntity forum;
  
  @OneToOne(
    optional = false, 
  fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "fk_student")
  private FMembershipEntity student;



    public int getCounter() {
        return counter;
    }

    public void setCounter(int count) {
        this.counter = count;
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

    public ForumEntity getForum() {
        return forum;
    }

    public void setForum(ForumEntity forum) {
        this.forum = forum;
    }


    public List<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public FMembershipEntity getStudent() {
        return student;
    }

    public void setStudent(FMembershipEntity student) {
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getValidate() {
        return validate;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }




  
}
