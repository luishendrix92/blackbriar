package com.bootcamp.blackbriar.controller;

import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;
import com.bootcamp.blackbriar.model.membership.MembershipRequest;
import com.bootcamp.blackbriar.model.membership.MembershipResponse;
import com.bootcamp.blackbriar.model.membershipForum.MembershipForumRequest;
import com.bootcamp.blackbriar.model.membershipForum.MembershipForumRest;
import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.repository.*;
import com.bootcamp.blackbriar.service.membership.MembershipService;
import com.bootcamp.blackbriar.service.membershipF.MembershipFService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;

@RestController
@RequestMapping(value = "/api/membershipsForums")
public class MembershipForumController {

        @Autowired
        MembershipRepository membershipRepository;

        @Autowired
        MembershipService membershipService;

        @Autowired
        ModelMapper modelMapper;

        @Autowired
        GroupRepository groupRepository;

        @Autowired
        UserRepository userRepository;

        @Autowired
        MembershipFService membershipFService;

        @Autowired
        ForumRepository forumRepository;


    @PostMapping(value = "/{membershipId}")
    public MembershipForumRest subscribeForum(
            @RequestBody MembershipForumRequest memberForumData,
            @PathVariable long membershipId
    ) {
        MembershipEntity member = membershipRepository.findById(membershipId).
                orElseThrow(()-> new EntityNotFoundException("The member not exist."));
        ForumEntity forum = forumRepository.findById(memberForumData.getForumId())
                .orElseThrow(() -> new EntityNotFoundException("The forum you want to join no longer exists."));

        return membershipFService.joinForum(member, forum);
    }
    }
