package com.bootcamp.blackbriar.service.membershipF;

import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;
import com.bootcamp.blackbriar.model.membershipForum.MembershipForumRest;

public interface MembershipFService {
    MembershipForumRest joinForum(MembershipEntity member, ForumEntity forum);

}
