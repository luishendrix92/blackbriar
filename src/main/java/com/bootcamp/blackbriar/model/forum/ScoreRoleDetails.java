package com.bootcamp.blackbriar.model.forum;

public class ScoreRoleDetails {
  private boolean warrior;
  private boolean healer;
  private boolean warlock;
  private int score;

  public boolean isWarrior() {
    return warrior;
  }

  public void setWarrior(boolean warrior) {
    this.warrior = warrior;
  }

  public boolean isHealer() {
    return healer;
  }

  public void setHealer(boolean healer) {
    this.healer = healer;
  }

  public boolean isWarlock() {
    return warlock;
  }

  public void setWarlock(boolean warlock) {
    this.warlock = warlock;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
