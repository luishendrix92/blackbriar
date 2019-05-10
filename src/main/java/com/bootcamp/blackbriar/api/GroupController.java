package com.bootcamp.blackbriar.api;

import com.bootcamp.blackbriar.business.GroupService;
import com.bootcamp.blackbriar.model.Group.GroupEntity;
import com.bootcamp.blackbriar.model.Group.GroupRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("groups") //http://localhost:8080/Groups
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping
    public String getGroup(){
        return "get groups was called";
    }

    @PostMapping
    public GroupEntity createGroup(@RequestBody GroupEntity groupData){
        //GroupRest returnValue = new GroupRest();
        GroupEntity createdGroup  = groupService.createGroup(groupData);

        //BeanUtils.copyProperties(createdGroup, returnValue);

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