package com.bootcamp.blackbriar.model.feedback;

import java.util.Date;

import com.bootcamp.blackbriar.model.user.UserRest;

public class FeedbackResponse {
    
    private long id;

    private String content;

    private Date created;

    private long answerId;

    private UserRest student;

    private UserRest owner;

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

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public UserRest getStudent() {
        return student;
    }

    public void setStudent(UserRest student) {
        this.student = student;
    }

    public UserRest getOwner() {
        return owner;
    }

    public void setOwner(UserRest owner) {
        this.owner = owner;
    }

  

}