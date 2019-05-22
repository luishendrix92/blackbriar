package com.bootcamp.blackbriar.controller;

import java.security.Principal;

import javax.persistence.EntityNotFoundException;

import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.membership.MembershipRequest;
import com.bootcamp.blackbriar.model.membership.MembershipResponse;
import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.repository.GroupRepository;
import com.bootcamp.blackbriar.repository.UserRepository;
import com.bootcamp.blackbriar.service.membership.MembershipService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/memberships")
public class MembershipController {
  @Autowired
  MembershipService membershipService;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  GroupRepository groupRepository;

  @Autowired
  UserRepository userRepository;

  @PostMapping
  public MembershipResponse subscribe(
    @RequestBody MembershipRequest membershipRequest,
    Principal authenticatedUser
  ) {
    String studentId = authenticatedUser.getName();
    UserEntity student = userRepository.findByUserId(studentId);
    GroupEntity group = groupRepository.findById(membershipRequest.getGroupId())
      .orElseThrow(() -> new EntityNotFoundException("The group you want to join no longer exists."));
    
    return membershipService.joinGroup(student, group);
  }
}
