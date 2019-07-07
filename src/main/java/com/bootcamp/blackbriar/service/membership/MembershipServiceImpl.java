package com.bootcamp.blackbriar.service.membership;

import javax.persistence.EntityNotFoundException;

import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;
import com.bootcamp.blackbriar.model.membership.MembershipResponse;
import com.bootcamp.blackbriar.model.user.UserEntity;
import com.bootcamp.blackbriar.repository.*;
import com.bootcamp.blackbriar.service.inbox.InboxService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipServiceImpl implements MembershipService {
  @Autowired
  MembershipRepository membershipRepository;

  @Autowired
  GroupRepository groupRepository;
  
  @Autowired
  UserRepository userRepository;

  @Autowired
  InboxService inboxService;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public MembershipResponse joinGroup(UserEntity student, GroupEntity group) {
    MembershipEntity existingMembership = membershipRepository.getByCompositeKey(student.getUserId(), group.getId());
    MembershipResponse response = new MembershipResponse();
    MembershipEntity newMembership;

    if (group.getOwner().getUserId().equals(student.getUserId())) {
      throw new RuntimeException("Instructors can't join their own grups as students.");
    } else if (existingMembership != null && existingMembership.isActive()) {
      throw new RuntimeException("You are already a member of this group.");
    } else if (
      existingMembership != null &&
      !existingMembership.isActive() &&
      !existingMembership.isInvitation()
    ) {
      throw new RuntimeException("Your membership request is pending approval.");
    } else if (
      existingMembership != null &&
      existingMembership.isInvitation() &&
      !existingMembership.isActive()
    ) {
      newMembership = existingMembership;

      newMembership.setActive(true);

      newMembership = membershipRepository.save(newMembership);
    } else {
      newMembership = new MembershipEntity();

      newMembership.setActive(group.isPublicGroup());
      newMembership.setGroup(group);
      newMembership.setStudent(student);

      newMembership = membershipRepository.save(newMembership);
    }

    response = modelMapper.map(newMembership, MembershipResponse.class);

    if (!group.isPublicGroup()) {
      inboxService.sendMessage(
        group.getOwner().getUserId(),
        newMembership.getId(),
        student.getFirstName() + " " + student.getLastName() + " wants to join your private group " + group.getTitle() + ".",
        "GPRQT"
      );

      response.setStatusMessage("Your membership request has been sent to " + group.getOwner().getFirstName() + " " + group.getOwner().getLastName() + ".");
    } else {
      response.setStatusMessage("You have successfully joined " + group.getTitle() + " group.");
    }

    return response;
  }

  @Override
  public MembershipEntity approveMembershipRequest(long membershipId, String instructorUserId) {
    MembershipEntity toApprove = membershipRepository.findById(membershipId)
      .orElseThrow(() -> new EntityNotFoundException("This membership or request does not exist."));

    if (toApprove.isActive()) {
      throw new RuntimeException("This membership is already active.");
    } else if (toApprove.isInvitation()) {
      throw new RuntimeException("The student must accept this invitation.");
    } else if (!toApprove.getGroup().getOwner().getUserId().equals(instructorUserId)) {
      throw new RuntimeException("You can only approve requests sent to your own groups.");
    }

    toApprove.setActive(true);

    MembershipEntity approved = membershipRepository.save(toApprove);

    inboxService.sendMessage(
      approved.getStudent().getUserId(),
      approved.getGroup().getId(),
      "You have been accepted into " + approved.getGroup().getTitle() + "!",
      "GPAPV"
    );

    return approved;
  }

  @Override
  public void rejectMembershipRequest(long membershipId, String instructorUserId) {
    MembershipEntity toDeny = membershipRepository.findById(membershipId)
      .orElseThrow(() -> new EntityNotFoundException("This membership does not exist."));
    
    if (toDeny.isActive()) {
      throw new RuntimeException("You can't deny an active membership.");
    } else if (toDeny.isInvitation()) {
      throw new RuntimeException("This membership is pending invitation acceptance.");
    } else if (!toDeny.getGroup().getOwner().getUserId().equals(instructorUserId)) {
      throw new RuntimeException("You can only deny requests sent to your own groups.");
    }

    membershipRepository.delete(toDeny);

    inboxService.sendMessage(
      toDeny.getStudent().getUserId(),
      null,
      "Your request to to join " + toDeny.getGroup().getTitle() + " has been denied.",
      "GNRIC"
    );
  }

  @Override
  public String leaveOrCancelMembership(long membershipId, String studentUserId) {
    MembershipEntity toRemove = membershipRepository.findById(membershipId)
      .orElseThrow(() -> new EntityNotFoundException("This membership does not exist."));
    String response;
    String message;

    if (!toRemove.getStudent().getUserId().equals(studentUserId)) {
      throw new RuntimeException("You can't remove a membership that isn't yours.");
    }

    if (!toRemove.isActive()) {
      if (toRemove.isInvitation()) {
        response = "You have successfully declined the invitation.";

        message = toRemove.getStudent().getFirstName() + " " +
          toRemove.getStudent().getLastName() +
          " has declined your invitation to join '" +
          toRemove.getGroup().getTitle() + ".'";
      } else {
        response = "You have successfully cancelled your request.";
        message = toRemove.getStudent().getFirstName() + " " +
          toRemove.getStudent().getLastName() +
          " no longer wishes to join '" +
          toRemove.getGroup().getTitle() + ".'";
      }
    } else {
      response = "You are no longer a member of '" + toRemove.getGroup().getTitle() + ".'";
      message =  toRemove.getStudent().getFirstName() + " " +
        toRemove.getStudent().getLastName() +
        " has abadoned your group '" +
        toRemove.getGroup().getTitle() + ".'";
    }

    membershipRepository.delete(toRemove);
    inboxService.sendMessage(toRemove.getGroup().getOwner().getUserId(), null, message, "GNRIC");

    return response;
  }
}
