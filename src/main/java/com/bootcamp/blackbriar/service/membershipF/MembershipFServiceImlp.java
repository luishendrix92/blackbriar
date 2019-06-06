package com.bootcamp.blackbriar.service.membershipF;

import javax.persistence.EntityNotFoundException;

import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;
import com.bootcamp.blackbriar.model.membership.MembershipResponse;
import com.bootcamp.blackbriar.model.membershipForum.MembershipForumEntity;
import com.bootcamp.blackbriar.model.membershipForum.MembershipForumRest;
import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.repository.*;
import com.bootcamp.blackbriar.service.inbox.InboxService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipFServiceImlp implements MembershipFService {
    @Autowired
    MembershipForumRepository membershipForumRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InboxService inboxService;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public MembershipForumRest joinForum(MembershipEntity member, ForumEntity forum) {
       // MembershipForumEntity existingMembershipF = membershipForumRepository.getByCompositeKey(member.getId(), forum.getId());
        MembershipForumRest response = new MembershipForumRest();
        MembershipForumEntity newMembershipF;
/**
        if (forum.getGroup().getOwner().getUserId().equals(member.getId())) {
            throw new RuntimeException("Instructors can't join their own forums as students.");
        } else if (existingMembershipF != null) {
            throw new RuntimeException("You are already a member of this forum.");
        }
       else {
            newMembershipF = new MembershipForumEntity();

            newMembershipF.setForum(forum);
            newMembershipF.setMemberGroup(member);

            newMembershipF = membershipForumRepository.save(newMembershipF);
        }
        **/
        newMembershipF = new MembershipForumEntity();

        newMembershipF.setForum(forum);
        newMembershipF.setMemberGroup(member);

        newMembershipF = membershipForumRepository.save(newMembershipF);
        response = modelMapper.map(newMembershipF, MembershipForumRest.class);


            response.setStatusMessage("You have successfully joined " + forum.getTitle() + " forum.");

        return response;
    }

}
