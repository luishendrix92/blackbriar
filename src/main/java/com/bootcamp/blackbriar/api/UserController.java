package com.bootcamp.blackbriar.api;

import com.bootcamp.blackbriar.business.group.GroupService;
import com.bootcamp.blackbriar.business.user.UserService;
import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.group.InstructorGroupResponse;
import com.bootcamp.blackbriar.model.user.UserDetailsRequestModel;
import com.bootcamp.blackbriar.model.user.UserDto;
import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.model.user.UserRest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  UserService userService;

  @Autowired
  GroupService groupService;

  @Autowired
  ModelMapper modelMapper;

  @GetMapping(value = "/{userId}")
  public UserRest getUser(@PathVariable String userId) {
    UserEntity user = userService.getUserByPublicId(userId);

    return modelMapper.map(user, UserRest.class);
  }

  @GetMapping(value = "/{instructorUserId}/groups/owned")
  public List<InstructorGroupResponse> instructorGroups(@PathVariable String instructorUserId) {
    List<GroupEntity> groups = groupService.getInstructorGroups(instructorUserId);
    Type withNoOwnerData = new TypeToken<List<InstructorGroupResponse>>(){}.getType();
    List<InstructorGroupResponse> serializedGroupList = modelMapper.map(groups, withNoOwnerData);

    return serializedGroupList;
  }

  @PostMapping(value = "/{instructorUserId}/groups/owned")
  public InstructorGroupResponse createGroup(
    @PathVariable String instructorUserId,
    @RequestBody GroupEntity groupData) {
    GroupEntity createdGroup = groupService.createGroup(instructorUserId, groupData);

    return modelMapper.map(createdGroup, InstructorGroupResponse.class);
  }

  @PostMapping
  public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
    UserRest returnValue = new UserRest();
    UserDto userDto = new UserDto();

    BeanUtils.copyProperties(userDetails, userDto);

    UserDto createdUser = userService.createUser(userDto);

    BeanUtils.copyProperties(createdUser, returnValue);

    return returnValue;
  }
}
