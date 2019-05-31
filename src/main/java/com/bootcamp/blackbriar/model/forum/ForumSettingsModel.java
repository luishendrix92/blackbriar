package com.bootcamp.blackbriar.model.forum;

import java.util.Date;

public class ForumSettingsModel {

    public ForumSettingsModel(){

    }

    public ForumSettingsModel(ForumSettingsEntity forumSettings_entity){
        this.id = forumSettings_entity.getId();
        this.startDateTime = forumSettings_entity.getStartDateTime();
        this.endDateTime = forumSettings_entity.getEndDateTime();
        this.fighterPoints = forumSettings_entity.getFighterPoints();
        this.healerPoints = forumSettings_entity.getHealerPoints();
        this.bloodmagePoints = forumSettings_entity.getBloodmagePoints();
        this.validResponsePoints = forumSettings_entity.getValidResponsePoints();
    }

    public ForumSettingsModel(
        long id,
        Date startDateTime,
        Date endDateTime,
        int fighterPoints,
        int healerPoints,
        int bloodmagePoints,
        int validResponsePoints){

        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.fighterPoints = fighterPoints;
        this.healerPoints = healerPoints;
        this.bloodmagePoints = bloodmagePoints;
        this.validResponsePoints = validResponsePoints;

    }

    private Long id;
    private Date startDateTime;
    private Date endDateTime;
    private int fighterPoints;
    private int healerPoints;
    private int bloodmagePoints;
    private int validResponsePoints;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getFighterPoints() {
        return fighterPoints;
    }

    public void setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
    }

    public int getHealerPoints() {
        return healerPoints;
    }

    public void setHealerPoints(int healerPoints) {
        this.healerPoints = healerPoints;
    }

    public int getBloodmagePoints() {
        return bloodmagePoints;
    }

    public void setBloodmagePoints(int bloodmagePoints) {
        this.bloodmagePoints = bloodmagePoints;
    }

    public int getValidResponsePoints() {
        return validResponsePoints;
    }

    public void setValidResponsePoints(int validResponsePoints) {
        this.validResponsePoints = validResponsePoints;
    }
}
