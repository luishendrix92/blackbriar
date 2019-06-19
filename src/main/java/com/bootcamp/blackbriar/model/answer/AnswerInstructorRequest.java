package com.bootcamp.blackbriar.model.answer;


public class AnswerInstructorRequest{

    private Boolean aprove;

    private long answerId;

  

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public Boolean getAprove() {
        return aprove;
    }

    public void setAprove(Boolean aprove) {
        this.aprove = aprove;
    }

    
}