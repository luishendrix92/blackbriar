package com.bootcamp.blackbriar.service.membership;

import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;
import com.bootcamp.blackbriar.model.membership.MembershipResponse;
import com.bootcamp.blackbriar.model.user.UserEntity;

public interface MembershipService {
  MembershipResponse joinGroup(UserEntity student, GroupEntity group);

  MembershipEntity approveMembershipRequest(long membershipId, String instructorUserId);

  void rejectMembershipRequest(long membershipId, String instructorUserId);

  String leaveOrCancelMembership(long membershipId, String studentUserId);
}
