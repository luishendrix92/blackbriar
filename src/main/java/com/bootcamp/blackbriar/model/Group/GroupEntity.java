package com.bootcamp.blackbriar.model.Group;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity(name="Group")
@Table(name="grp")
public class GroupEntity implements Serializable {
    private @Id @GeneratedValue  long id;
    private String userId;
    private String title;
    private String description;
    private String image;
    private static final long serialVersionUID = 3;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getuserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
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

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }


    GroupEntity(String userId, String title, String description, String image){
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.image = image;
    }
}
