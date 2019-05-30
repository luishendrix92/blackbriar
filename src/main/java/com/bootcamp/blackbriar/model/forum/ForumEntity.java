package com.bootcamp.blackbriar.model.forum;


import com.bootcamp.blackbriar.model.group.GroupEntity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "forum")
public class ForumEntity implements Serializable {

    public ForumEntity(){

    }

    public ForumEntity(
            long id,
            String title,
            String description,
            String content,
            GroupEntity group,
            ForumSettingsEntity forumSettings,
            Date createdDateTime,
            Date updatedDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.group = group;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "visible", nullable = false)
    private boolean visible = false;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDateTime;



    // Foreign Keys

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_group")
    private GroupEntity group;

    // One To One Relation with Forum Settings

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL,
        fetch = FetchType.LAZY, optional = false)

    private ForumSettingsEntity forumSettings;

    public void setForumSettings(ForumSettingsEntity forumSettings){
        if (forumSettings == null){
            if (this.forumSettings != null){
                this.forumSettings.setForum(null);
            }
        }else{
            forumSettings.setForum(this);
        }
        this.forumSettings = forumSettings;
    }


    // Setters and Getters


    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Date getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Date updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public ForumSettingsEntity getForumSettings() {
        return forumSettings;
    }
}

