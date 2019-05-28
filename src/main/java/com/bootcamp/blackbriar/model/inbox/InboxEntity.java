package com.bootcamp.blackbriar.model.inbox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import com.bootcamp.blackbriar.model.user.UserEntity;

import javax.persistence.*;

@Entity(name = "Inbox")
public class InboxEntity implements Serializable {
  private static final long serialVersionUID = 1584476355380147867L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToMany(mappedBy = "inbox")
  private List<MessageEntity> messages = new ArrayList<MessageEntity>();

  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_user")
  private UserEntity subject;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<MessageEntity> getMessages() {
    return messages;
  }

  public void setMessages(List<MessageEntity> messages) {
    this.messages = messages;
  }
}
