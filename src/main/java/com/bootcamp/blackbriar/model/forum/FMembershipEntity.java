package com.bootcamp.blackbriar.model.forum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.amazonaws.services.guardduty.model.Feedback;
import com.bootcamp.blackbriar.model.answer.AnswerEntity;
import com.bootcamp.blackbriar.model.feedback.FeedbackEntity;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

@Entity(name = "ForumMembership")
@Table(name = "membershipf")
public class FMembershipEntity implements Serializable {
  private static final long serialVersionUID = 6045506483957768888L;

  public FMembershipEntity(MembershipEntity member, ForumEntity forum) {
    this.member = member;
    this.forum = forum;
  }

  public FMembershipEntity() {}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_membership")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private MembershipEntity member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_forum")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ForumEntity forum;

  @OneToOne(
    fetch = FetchType.LAZY,
    mappedBy = "student",
    cascade = CascadeType.ALL
  )
  private AnswerEntity answer;

  @OneToMany(
    mappedBy = "student",
      fetch = FetchType.LAZY
  )
  private List<FeedbackEntity> feedbacks = new ArrayList<FeedbackEntity>();


  @Column(nullable = false)
  private boolean warrior = false;

  @Column(nullable = false)
  private boolean healer = false;

  @Column(nullable = false)
  private boolean warlock = false;

  @Column(nullable = false)
  private int score = 0;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public MembershipEntity getMember() {
    return member;
  }

  public void setMember(MembershipEntity member) {
    this.member = member;
  }

  public ForumEntity getForum() {
    return forum;
  }

  public void setForum(ForumEntity forum) {
    this.forum = forum;
  }

  public boolean isHealer() {
    return healer;
  }

  public void setHealer(boolean healer) {
    this.healer = healer;
  }

  public boolean isWarlock() {
    return warlock;
  }

  public void setWarlock(boolean warlock) {
    this.warlock = warlock;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public boolean isWarrior() {
    return warrior;
  }

  public void setWarrior(boolean warrior) {
    this.warrior = warrior;
  }

  public AnswerEntity getAnswer() {
    return answer;
  }

  public void setAnswer(AnswerEntity answer) {
    this.answer = answer;
  }

  public List<FeedbackEntity> getFeedbacks() {
    return feedbacks;
  }

  public void setFeedbacks(List<FeedbackEntity> feedbacks) {
    this.feedbacks = feedbacks;
  }

 }
