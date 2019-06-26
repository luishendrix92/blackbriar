package com.bootcamp.blackbriar.model.comments;

import java.util.Date;

import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.model.user.UserRest;

import org.springframework.beans.BeanUtils;

public class FeedbackResponse {
  private long id;
  private long parentId;
  private String content;
  private String files;
  private boolean approved;
  private UserRest studentDetails;
  private UserRest instructorDetails;
  private Date created;
  private Date updated;

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

  public UserRest getStudentDetails() {
    return studentDetails;
  }

  public UserRest getInstructorDetails() {
    return instructorDetails;
  }

  public void setStudent(FMembershipEntity student) {
    if (student != null) {
      UserRest details = new UserRest();
      BeanUtils.copyProperties(student.getMember().getStudent(), details);
      this.studentDetails = details;
    } else {
      this.studentDetails = null;
    }
  }

  public void setInstructor(UserEntity instructor) {
    if (instructor != null) {
      UserRest details = new UserRest();
      BeanUtils.copyProperties(instructor, details);
      this.instructorDetails = details;
    } else {
      this.instructorDetails = null;
    }
  }

  public void setParent(AnswerEntity parent) {
    this.parentId = parent.getId();
  }

  public boolean isApproved() {
    return approved;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public long getParentId() {
    return parentId;
  }

  public String getFiles() {
    return files;
  }

  public void setFiles(String files) {
    this.files = files;
  }
}
