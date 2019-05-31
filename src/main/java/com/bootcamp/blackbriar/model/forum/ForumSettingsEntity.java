package com.bootcamp.blackbriar.model.forum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "forumSettings")
public class ForumSettingsEntity implements Serializable {

    public ForumSettingsEntity(){

    }

    public ForumSettingsEntity(Date startDateTime, Date endDateTime, int fighterPoints, int healerPoints, int bloodmagePoints, int validResponsePoints) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.fighterPoints = fighterPoints;
        this.healerPoints = healerPoints;
        this.bloodmagePoints = bloodmagePoints;
        this.validResponsePoints = validResponsePoints;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "startDateTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;

    @Column(name = "endDateTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;

    @Column(name = "fighterPoints",nullable = false)
    private int fighterPoints;

    @Column(name = "healerPoints",nullable = false)
    private int healerPoints;

    @Column(name = "bloodmagePoints",nullable = false)
    private int bloodmagePoints;

    @Column(name = "validResponsePoints",nullable = false)
    private int validResponsePoints;


    // One to One relation with Forum

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_forum")
    private ForumEntity forum;



    // Setters and Getters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getStartDateTime() { return startDateTime; }
    public void setStartDateTime(Date startDateTime) { this.startDateTime = startDateTime; }

    public Date getEndDateTime() { return endDateTime; }
    public void setEndDateTime(Date endDateTime) { this.endDateTime = endDateTime; }

    public int getFighterPoints() { return fighterPoints; }
    public void setFighterPoints(int fighterPoints) { this.fighterPoints = fighterPoints; }

    public int getHealerPoints() { return healerPoints; }
    public void setHealerPoints(int healerPoints) { this.healerPoints = healerPoints; }

    public int getBloodmagePoints() { return bloodmagePoints; }
    public void setBloodmagePoints(int bloodmagePoints) { this.bloodmagePoints = bloodmagePoints; }

    public int getValidResponsePoints() { return validResponsePoints; }
    public void setValidResponsePoints(int validResponsePoints) { this.validResponsePoints = validResponsePoints; }

    public ForumEntity getForum() {
        return forum;
    }

    public void setForum(ForumEntity forum) {
        this.forum = forum;
    }
}
