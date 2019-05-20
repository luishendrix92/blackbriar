package com.bootcamp.blackbriar.service.user;

import java.util.List;

import com.bootcamp.blackbriar.model.membership.MembershipDetails;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;
import com.bootcamp.blackbriar.model.user.GroupMemberResponse;
import com.bootcamp.blackbriar.model.user.UserDto;
import com.bootcamp.blackbriar.model.user.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  UserDto createUser(UserDto userDto);
  UserDto getUser(String email);

  UserEntity getUserByPublicId(String userId);

  List<GroupMemberResponse> getGroupMembers(long groupId);

  MembershipDetails createSubscription(long studentId, long groupId);
}
