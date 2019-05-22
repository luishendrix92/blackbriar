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
    MembershipResponse newMembership = new MembershipResponse();
    MembershipEntity membership = new MembershipEntity();

    if (group.getOwner().getUserId().equals(student.getUserId())) {
      throw new RuntimeException("Instructors can't join their own grups as students.");
    } else if (existingMembership != null && existingMembership.isActive()) {
      throw new RuntimeException("You are already a member of this group.");
    }

    membership.setActive(true);
    membership.setGroup(group);
    membership.setStudent(student);

    membership = membershipRepository.save(membership);

    newMembership = modelMapper.map(membership, MembershipResponse.class);
    newMembership.setStatusMessage("You have successfully joined.");

    return newMembership;
  }
}
