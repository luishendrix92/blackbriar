package com.bootcamp.blackbriar.model.forum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "ForumSettings")
@Table(name = "fsettings")
public class ForumSettingsEntity implements Serializable {
  private static final long serialVersionUID = 6900396010121873882L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date startDate;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date endDate;

  @Column(nullable = false)
  private int warriorPoints;

  @Column(nullable = false)
  private int healerPoints;

  @Column(nullable = false)
  private int warlockPoints;

  @Column(nullable = false)
  private int validResponsePoints;

  /**
   * One to One Relationship: [ForumSettings]<-[Forum]
   * =================================================
   * This set of settings and config variables belong
   * to a single forum activity.
   */
  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_forum")
  private ForumEntity forum;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

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

  public ForumEntity getForum() {
    return forum;
  }

  public void setForum(ForumEntity forum) {
    this.forum = forum;
  }
}
