package com.bootcamp.blackbriar.business;


import com.bootcamp.blackbriar.model.Group.GroupEntity;
import com.bootcamp.blackbriar.model.Group.GroupRest;
import com.bootcamp.blackbriar.repository.GroupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Override
    public GroupEntity createGroup(GroupEntity group){

        GroupEntity storedGroupDetails = groupRepository.save(group);

        return storedGroupDetails;
    }

}
