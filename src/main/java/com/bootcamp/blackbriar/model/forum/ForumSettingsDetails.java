package com.bootcamp.blackbriar.model.forum;

import java.util.Date;

public class ForumSettingsDetails {
  private Date startDate;
  private Date endDate;
  private int fighterPoints;
  private int healerPoints;
  private int bloodmagePoints;
  private int validResponsePoints;

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
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
