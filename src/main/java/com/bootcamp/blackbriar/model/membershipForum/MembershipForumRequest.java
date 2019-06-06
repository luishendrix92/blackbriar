package com.bootcamp.blackbriar.model.membershipForum;

public class MembershipForumRequest {
    private long memberId;

    private long forumId;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getForumId() {
        return forumId;
    }

    public void setForumId(long forumId) {
        this.forumId = forumId;
    }
}
