package com.bootcamp.blackbriar.model.comments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.user.UserRest;
import org.springframework.beans.BeanUtils;

public class AnswerResponse {
  private long id;
  private String content;
  private String files;
  private Boolean approved;
  private UserRest studentDetails;
  private Date created;
  private Date updated;
  
  private List<FeedbackResponse> replies = new ArrayList<FeedbackResponse>();

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public List<FeedbackResponse> getReplies() {
    return replies;
  }

  public void setReplies(List<FeedbackResponse> replies) {
    this.replies = replies;
  }

  public Boolean getApproved() {
    return approved;
  }

  public void setApproved(Boolean approved) {
    this.approved = approved;
  }

  public UserRest getStudentDetails() {
    return this.studentDetails;
  }

  public void setStudent(FMembershipEntity student) {
    UserRest details = new UserRest();
    BeanUtils.copyProperties(student.getMember().getStudent(), details);
    this.studentDetails = details;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public String getFiles() {
    return files;
  }

  public void setFiles(String files) {
    this.files = files;
  }
}
