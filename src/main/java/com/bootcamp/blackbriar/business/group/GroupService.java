package com.bootcamp.blackbriar.business.group;

import com.bootcamp.blackbriar.model.group.GroupEntity;

import java.util.List;

public interface GroupService {
  GroupEntity createGroup(GroupEntity group);

  List<GroupEntity> getAllGroups();

  List<GroupEntity> getInstructorGroups(String instructorUserId);
}
