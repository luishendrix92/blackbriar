package com.bootcamp.blackbriar.controller;

import com.bootcamp.blackbriar.service.group.GroupService;
import com.bootcamp.blackbriar.service.user.UserService;
import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.group.GroupResponse;
import com.bootcamp.blackbriar.model.group.InstructorGroupResponse;
import com.bootcamp.blackbriar.model.group.StudentGroupResponse;
import com.bootcamp.blackbriar.model.user.GroupMemberResponse;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/groups")
public class GroupController {
  @Autowired
  GroupService groupService;

  @Autowired
  UserService userService;

  @Autowired
  ModelMapper modelMapper;

  @GetMapping
  public List<StudentGroupResponse> searchAndExplore(Principal auth) {
    return groupService.exploreGroups(auth.getName());
  }

  @GetMapping(value = "/all")
  public List<GroupResponse> allGroups() {
    List<GroupEntity> groups = groupService.getAllGroups();
    Type withOwnerData = new TypeToken<List<GroupResponse>>(){}.getType();
    List<GroupResponse> serializedGroupList = modelMapper.map(groups, withOwnerData);

    return serializedGroupList;
  }

  @GetMapping(value = "/{groupId}/students")
  public List<GroupMemberResponse> groupMembers(@PathVariable long groupId) {
    return userService.getGroupMembers(groupId);
  }

  @GetMapping(value = "/{groupId}")
  public GroupResponse singleGroup(@PathVariable long groupId) {
    GroupEntity group = groupService.getGroup(groupId);

    return modelMapper.map(group, GroupResponse.class);
  }

  @PostMapping
  public InstructorGroupResponse createGroup(
    @RequestBody GroupEntity groupData,
    Principal auth
  ) {
    GroupEntity createdGroup = groupService.createGroup(auth.getName(), groupData);

    return modelMapper.map(createdGroup, InstructorGroupResponse.class);
  }

  @PutMapping
  public String updateGroup(){
    return "update group was called";
  }

  @DeleteMapping
  public String deleteGroup(){
    return "delete group was called";
  }
}
