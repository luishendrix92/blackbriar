package com.bootcamp.blackbriar.service.membership;

import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.membership.MembershipResponse;
import com.bootcamp.blackbriar.model.user.UserEntity;

public interface MembershipService {
  MembershipResponse joinGroup(UserEntity student, GroupEntity group);
}
