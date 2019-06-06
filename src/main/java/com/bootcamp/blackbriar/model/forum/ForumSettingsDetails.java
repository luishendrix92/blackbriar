package com.bootcamp.blackbriar.model.forum;

import java.util.Date;

public class ForumSettingsDetails {
  private Date startDate;
  private Date endDate;
  private int warriorPoints;
  private int healerPoints;
  private int warlockPoints;
  private int validResponsePoints;

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public int getHealerPoints() {
    return healerPoints;
  }

  public void setHealerPoints(int healerPoints) {
    this.healerPoints = healerPoints;
  }

  public int getValidResponsePoints() {
    return validResponsePoints;
  }

  public void setValidResponsePoints(int validResponsePoints) {
    this.validResponsePoints = validResponsePoints;
  }

  public int getWarriorPoints() {
    return warriorPoints;
  }

  public void setWarriorPoints(int warriorPoints) {
    this.warriorPoints = warriorPoints;
  }

  public int getWarlockPoints() {
    return warlockPoints;
  }

  public void setWarlockPoints(int warlockPoints) {
    this.warlockPoints = warlockPoints;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
}
