package com.bootcamp.blackbriar.service.group;

import com.bootcamp.blackbriar.service.group.GroupService;
import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.group.GroupUpdateRequest;
import com.bootcamp.blackbriar.model.group.StudentGroupResponse;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;
import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.repository.GroupRepository;
import com.bootcamp.blackbriar.repository.MembershipRepository;
import com.bootcamp.blackbriar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
  @Autowired
  GroupRepository groupRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  MembershipRepository membershipRepository;

  @Override
  public GroupEntity createGroup(String instructorUserId, GroupEntity group)  {
    UserEntity groupOwner = userRepository.findByUserId(instructorUserId);

    if (groupOwner == null) {
      throw new EntityNotFoundException("The user who wishes to create the group does not exist.");
    }

    group.setOwner(groupOwner);

    return groupRepository.save(group);
  }

  @Override
  public GroupEntity updateGroup(long groupId, GroupUpdateRequest groupDetails, String userId){
    
    GroupEntity updatedGroup = groupRepository.findById(groupId)
    .orElseThrow(() -> new EntityNotFoundException("This group does not exist."));

    if(userId.equals(updatedGroup.getOwner().getUserId())){
      throw new EntityNotFoundException("The user who wishes to updated the group does not have permission.");
    }

    updatedGroup.setImage(groupDetails.getImage());
    updatedGroup.setDescription(groupDetails.getDescription());
    updatedGroup.setPublicGroup(groupDetails.isPublicGroup());

    if(updatedGroup.isPublicGroup()){
      List<MembershipEntity> members = updatedGroup.getMembers();
      updateMemberships(members);
    }

    return groupRepository.save(updatedGroup);

  }

  @Override
  public List<GroupEntity> getAllGroups() {
    return (List<GroupEntity>) groupRepository.findAll();
  }

  @Override
  public List<GroupEntity> getInstructorGroups(String instructorUserId) {
    return groupRepository.findByOwnerUserId(instructorUserId);
  }

  @Override
  public List<StudentGroupResponse> getStudentGroups(String studentUserId) {
    return groupRepository.findByStudentUserId(studentUserId);
  }

  @Override
  public GroupEntity getGroup(long groupId) {
    return groupRepository.findById(groupId)
      .orElseThrow(() -> new EntityNotFoundException("This group was removed or doesn't exist."));
  }

  @Override
  public List<StudentGroupResponse> exploreGroups(String userId) {
    return groupRepository.getGroupsWithMembershipDetails(userId);
  }

  public void updateMemberships(List<MembershipEntity> members){
    for (MembershipEntity elem : members) {
      elem.setActive(true);
      membershipRepository.save(elem);
    }
  }
}
