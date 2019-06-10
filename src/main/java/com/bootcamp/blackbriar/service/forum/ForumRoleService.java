package com.bootcamp.blackbriar.service.forum;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.bootcamp.blackbriar.model.forum.FMembershipEntity;
import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.membership.MembershipEntity;
import com.bootcamp.blackbriar.repository.FMembershipRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForumRoleService {
  @Autowired
  private FMembershipRepository roleRepository;

  public List<FMembershipEntity> assignRoles(ForumEntity forum) {
    List<MembershipEntity> forumGroupMembers = forum.getGroup().getMembers();
    int groupMemberCount = forumGroupMembers.size();
    int healerAmount  = (int) Math.ceil(groupMemberCount * 0.30);
    int warriorAmount = (int) Math.ceil(groupMemberCount * 0.20);
    int warlockAmount = (int) Math.floor(groupMemberCount * 0.20);
    List<FMembershipEntity> forumMembers = forumGroupMembers.stream()
      .filter(groupMember -> groupMember.isActive())
      .map(activeMember -> new FMembershipEntity(activeMember, forum))
      .collect(Collectors.toList());

    actionAtRandom(
      forumMembers,
      forumMember -> forumMember.setHealer(true),
      healerAmount
    );

    actionAtRandom(
      forumMembers,
      forumMember -> forumMember.setWarrior(true),
      warriorAmount
    );

    actionAtRandom(
      forumMembers,
      forumMember -> forumMember.setWarlock(true),
      warlockAmount > 0 ? warlockAmount : 1
    );
    
    return (List<FMembershipEntity>) roleRepository.saveAll(forumMembers);
  }

  private <T> void actionAtRandom(List<T> source, Consumer<T> action, int amount) {
    int sourceCount = source.size();

    if (sourceCount <= 0 || amount <= 0) {
      return;
    }

    int pickedIndex = (int) Math.floor(Math.random() * sourceCount);
    T pickedItem = source.get(pickedIndex);
    
    action.accept(pickedItem);

    actionAtRandom(
      source.stream()
        .filter(item -> item != pickedItem)
        .collect(Collectors.toList()),
      action,
      amount - 1
    );
  }
}
