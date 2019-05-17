package com.bootcamp.blackbriar.service.group;

import com.bootcamp.blackbriar.service.group.GroupService;
import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.repository.GroupRepository;
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

  @Override
  public GroupEntity createGroup(String instructorUserId, GroupEntity group)  {
    UserEntity groupOwner = userRepository.findByUserId(instructorUserId);

    if (groupOwner == null) {
      throw new EntityNotFoundException("The user who wishes to create the group does not exist.");
    }

    group.setOwner(groupOwner);

    GroupEntity newGroup = groupRepository.save(group);

    return newGroup;
  }

  @Override
  public List<GroupEntity> getAllGroups() {
    List<GroupEntity> groups = (List<GroupEntity>) groupRepository.findAll();

    return groups;
  }

  @Override
  public List<GroupEntity> getInstructorGroups(String instructorUserId) {
    List<GroupEntity> groups = groupRepository.findByOwnerUserId(instructorUserId);

    return groups;
  }

  @Override
  public GroupEntity getGroup(long groupId) {
    GroupEntity group = groupRepository.findById(groupId)
      .orElseThrow(() -> new EntityNotFoundException("This group was removed or doesn't exist."));

    return group;
  }

}
