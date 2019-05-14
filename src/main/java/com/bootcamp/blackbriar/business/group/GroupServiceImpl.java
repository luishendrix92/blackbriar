package com.bootcamp.blackbriar.business.group;

import com.bootcamp.blackbriar.business.group.GroupService;
import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.repository.GroupRepository;
import com.bootcamp.blackbriar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
  @Autowired
  GroupRepository groupRepository;

  @Autowired
  UserRepository userRepository;

  @Override
  public GroupEntity createGroup(GroupEntity group) {
    UserEntity groupOwner = userRepository.findById(1L).get();

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

}
