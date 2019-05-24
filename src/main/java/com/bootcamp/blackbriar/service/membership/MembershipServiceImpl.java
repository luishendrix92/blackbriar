package com.bootcamp.blackbriar.service.membership;

import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;
import com.bootcamp.blackbriar.model.membership.MembershipResponse;
import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.repository.GroupRepository;
import com.bootcamp.blackbriar.repository.MembershipRepository;
import com.bootcamp.blackbriar.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipServiceImpl implements MembershipService {
  @Autowired
  MembershipRepository membershipRepository;

  @Autowired
  GroupRepository groupRepository;
  
  @Autowired
  UserRepository userRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public MembershipResponse joinGroup(UserEntity student, GroupEntity group) {
    MembershipEntity existingMembership = membershipRepository.getByCompositeKey(student.getUserId(), group.getId());
    MembershipResponse response = new MembershipResponse();
    MembershipEntity newMembership;

    if (group.getOwner().getUserId().equals(student.getUserId())) {
      throw new RuntimeException("Instructors can't join their own grups as students.");
    } else if (existingMembership != null && existingMembership.isActive()) {
      throw new RuntimeException("You are already a member of this group.");
    } else if (
      existingMembership != null &&
      !existingMembership.isActive() &&
      !existingMembership.isInvitation()
    ) {
      throw new RuntimeException("Your membership request is pending approval.");
    } else if (
      existingMembership != null &&
      existingMembership.isInvitation() &&
      !existingMembership.isActive()
    ) {
      newMembership = existingMembership;

      newMembership.setActive(true);

      newMembership = membershipRepository.save(newMembership);
    } else {
      newMembership = new MembershipEntity();

      newMembership.setActive(group.isPublicGroup());
      newMembership.setGroup(group);
      newMembership.setStudent(student);

      newMembership = membershipRepository.save(newMembership);
    }

    response = modelMapper.map(newMembership, MembershipResponse.class);

    if (!group.isPublicGroup()) {
      response.setStatusMessage("Your membership request has been sent to " + group.getOwner().getFirstName() + " " + group.getOwner().getLastName() + ".");
    } else {
      response.setStatusMessage("You have successfully joined " + group.getTitle() + " group.");
    }

    return response;
  }
}
