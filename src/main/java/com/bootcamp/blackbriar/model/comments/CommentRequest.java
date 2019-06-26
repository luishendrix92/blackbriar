package com.bootcamp.blackbriar.model.comments;

public class CommentRequest {
  private String content;
  private String files;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getFiles() {
    return files;
  }

  public void setFiles(String files) {
    this.files = files;
  }
}
