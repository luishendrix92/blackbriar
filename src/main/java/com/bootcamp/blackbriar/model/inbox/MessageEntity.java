package com.bootcamp.blackbriar.model.inbox;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "Message")
public class MessageEntity implements Serializable {
  private static final long serialVersionUID = -1098131002851864143L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_inbox")
  private InboxEntity inbox;

  private String category;

  @Column(nullable = false)
  private String content;

  private boolean archived = false;

  @Column(nullable = true)
  private Long actionRef;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
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

  public Long getActionRef() {
    return actionRef;
  }

  public void setActionRef(Long actionRef) {
    this.actionRef = actionRef;
  }

  public InboxEntity getInbox() {
    return inbox;
  }

  public void setInbox(InboxEntity inbox) {
    this.inbox = inbox;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public boolean isArchived() {
    return archived;
  }

  public void setArchived(boolean archived) {
    this.archived = archived;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }
}
