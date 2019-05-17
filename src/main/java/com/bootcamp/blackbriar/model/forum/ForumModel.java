/**
 * User: Alexis M. Gutierrez Kinto
 * Date: 10/05/19
 */

package com.bootcamp.blackbriar.model.forum;

import java.time.LocalDateTime;

public class ForumModel {

    public ForumModel(){

    }

    public ForumModel(ForumEntity forum_entity){
        this.id = forum_entity.getId();
        this.title = forum_entity.getTitle();
        this.description = forum_entity.getDescription();
    }

    public ForumModel(long id, String title, String description, LocalDateTime createDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    private long id;
    private String title;
    private String description;

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
}
