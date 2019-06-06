package com.bootcamp.blackbriar.model.membershipForum;


import com.bootcamp.blackbriar.model.membership.MembershipResponse;

public class MembershipForumRest {
    private long id;
    private MembershipResponse member;
    private boolean fighterRol;
    private boolean healerRol;
    private boolean bloodmageRol;
    private float score;
    private String statusMessage = "No message assigned...";

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MembershipResponse getMember() {
        return member;
    }

    public void setMember(MembershipResponse member) {
        this.member = member;
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
