package com.bootcamp.blackbriar.model.forum;

import java.util.Date;

public class ForumEditRequest {
  // Forum Entity Properties
  private String description;
  private String content;

  // Settings Entity Properties
  private Date endDate;
  private int warriorPoints;
  private int healerPoints;
  private int warlockPoints;
  private int validResponsePoints;

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

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public int getWarriorPoints() {
    return warriorPoints;
  }

  public void setWarriorPoints(int warriorPoints) {
    this.warriorPoints = warriorPoints;
  }

  public int getHealerPoints() {
    return healerPoints;
  }

  public void setHealerPoints(int healerPoints) {
    this.healerPoints = healerPoints;
  }

  public int getWarlockPoints() {
    return warlockPoints;
  }

  public void setWarlockPoints(int warlockPoints) {
    this.warlockPoints = warlockPoints;
  }

  public int getValidResponsePoints() {
    return validResponsePoints;
  }

  public void setValidResponsePoints(int validResponsePoints) {
    this.validResponsePoints = validResponsePoints;
  }
}
