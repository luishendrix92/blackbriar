package com.bootcamp.blackbriar.model.answer;


public class AnswerRequest{

    private String content;

    private long studentId;

    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
    
}