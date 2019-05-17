package com.bootcamp.blackbriar.service.group;

import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.group.StudentGroupResponse;

import java.util.List;

public interface GroupService {
  GroupEntity createGroup(String instructorUserId, GroupEntity group);

  List<GroupEntity> getAllGroups();

  List<GroupEntity> getInstructorGroups(String instructorUserId);

  GroupEntity getGroup(long groupId);

  List<StudentGroupResponse> getStudentGroups(String studentUserId);
}
