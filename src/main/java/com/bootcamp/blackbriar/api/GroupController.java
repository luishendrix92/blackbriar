package com.bootcamp.blackbriar.api;

import com.bootcamp.blackbriar.business.group.GroupService;
import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.group.GroupResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(value = "/api/groups")
public class GroupController {
  @Autowired
  GroupService groupService;

  @Autowired
  ModelMapper modelMapper;

  @GetMapping
  public List<GroupResponse> allGroups() {
    List<GroupEntity> groups = groupService.getAllGroups();
    Type withOwnerData = new TypeToken<List<GroupResponse>>(){}.getType();
    List<GroupResponse> serializedGroupList = modelMapper.map(groups, withOwnerData);

    return serializedGroupList;
  }

  @PostMapping
  public GroupEntity createGroup(@RequestBody GroupEntity groupData) {
    GroupEntity createdGroup  = groupService.createGroup(groupData);

    return createdGroup;
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