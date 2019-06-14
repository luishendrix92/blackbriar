package com.bootcamp.blackbriar.model.answer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bootcamp.blackbriar.model.feedback.FeedbackResponse;
import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.user.UserRest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AnswerResponse {

    @Autowired
    private ModelMapper modelMapper;

    private String content;

    private String message;
    
    private long id;

    private Date created;

    private UserRest student;

    private Boolean validate;

    private List<FeedbackResponse> feedbacks = new ArrayList<FeedbackResponse>();

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<FeedbackResponse> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackResponse> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public UserRest getStudent() {
        return student;
    }

    public void setStudent(FMembershipEntity student) {
        this.student = modelMapper.map(student.getMember().getStudent(), UserRest.class);
    }

    public Boolean getValidate() {
        return validate;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }

}