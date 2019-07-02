package com.bootcamp.blackbriar.service.group;

import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.group.GroupUpdateRequest;
import com.bootcamp.blackbriar.model.group.StudentGroupResponse;

import java.util.List;

public interface GroupService {
  GroupEntity createGroup(String instructorUserId, GroupEntity group);

  void deleteGroup(long groupId, String userId);

  List<GroupEntity> getAllGroups();

  List<GroupEntity> getInstructorGroups(String instructorUserId);

  GroupEntity getGroup(long groupId);

  List<StudentGroupResponse> getStudentGroups(String studentUserId);

  List<StudentGroupResponse> exploreGroups(String userId);

  GroupEntity updateGroup(long groupId, GroupUpdateRequest groupDetails, String userId);
}
