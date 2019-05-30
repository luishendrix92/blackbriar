package com.bootcamp.blackbriar.model.forum;


import com.bootcamp.blackbriar.model.group.GroupEntity;
import java.util.Date;

public class ForumModel {

    public ForumModel(){

    }

    public ForumModel(ForumEntity forum_entity){
        this.id = forum_entity.getId();
        this.title = forum_entity.getTitle();
        this.description = forum_entity.getDescription();
        this.content = forum_entity.getContent();
        this.group = forum_entity.getGroup();
        this.createdDateTime = forum_entity.getCreatedDateTime();
        this.updatedDateTime = forum_entity.getUpdatedDateTime();
    }

    public ForumModel(
        long id,
        String title,
        String description,
        String content,
        GroupEntity group,
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

    private long id;
    private String title;
    private String description;
    private String content;
    private GroupEntity group;
    private Date createdDateTime;
    private Date updatedDateTime;

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
