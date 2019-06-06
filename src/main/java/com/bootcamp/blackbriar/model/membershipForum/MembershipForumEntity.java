package com.bootcamp.blackbriar.model.membershipForum;

import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "MembershipForum")
@Table(name = "membershipForum")
public class MembershipForumEntity implements Serializable {
    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_memberGroup")
    private MembershipEntity memberGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_forum")
    private ForumEntity forum;

    @Column(nullable = false)
    private boolean fighterRol = false;

    @Column(nullable = false)
    private boolean healerRol = false;

    @Column(nullable = false)
    private boolean bloodmageRol = false;

    @Column(nullable = false)
    private float score = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MembershipEntity getMemberGroup() {
        return memberGroup;
    }

    public void setMemberGroup(MembershipEntity memberGroup) {
        this.memberGroup = memberGroup;
    }

    public ForumEntity getForum() {
        return forum;
    }

    public void setForum(ForumEntity forum) {
        this.forum = forum;
    }

    public boolean isFighterRol() {
        return fighterRol;
    }

    public void setFighterRol(boolean fighterRol) {
        this.fighterRol = fighterRol;
    }

    public boolean isHealerRol() {
        return healerRol;
    }

    public void setHealerRol(boolean healerRol) {
        this.healerRol = healerRol;
    }

    public boolean isBloodmageRol() {
        return bloodmageRol;
    }

    public void setBloodmageRol(boolean bloodmageRol) {
        this.bloodmageRol = bloodmageRol;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }


}
