/**
 * User: Alexis M. Gutierrez Kinto
 * Date: 10/05/19
 */

package com.bootcamp.blackbriar.model.forum;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.io.Serializable;

@Table(name = "forum")
@Entity
public class ForumEntity implements Serializable {

    public ForumEntity(){

    }

    public ForumEntity(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "title" , nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false, length = 1000)
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
