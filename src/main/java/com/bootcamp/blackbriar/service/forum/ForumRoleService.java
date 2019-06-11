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

  public List<FMembershipEntity> initMembership(ForumEntity forum) {
    List<MembershipEntity> forumGroupMembers = forum.getGroup().getMembers();
    List<FMembershipEntity> forumMembers = forumGroupMembers.stream()
      .filter(groupMember -> groupMember.isActive())
      .map(activeMember -> new FMembershipEntity(activeMember, forum))
      .collect(Collectors.toList());
    
    assignRoles(forumMembers, forumGroupMembers.size());
    
    return (List<FMembershipEntity>) roleRepository.saveAll(forumMembers);
  }

  private void assignRoles(List<FMembershipEntity> forumMembers, int memberCount) {
    int healerCount  = (int) Math.ceil(memberCount * 0.30);
    int warriorCount = (int) Math.ceil(memberCount * 0.20);
    int warlockCount = (int) Math.floor(memberCount * 0.20);

    warlockCount = warlockCount > 0 ? warlockCount : 1;

    actionAtRandom(
      forumMembers,
      forumMember -> forumMember.setHealer(true),
      healerCount
    );

    actionAtRandom(
      forumMembers,
      forumMember -> forumMember.setWarrior(true),
      warriorCount
    );

    actionAtRandom(
      forumMembers,
      forumMember -> forumMember.setWarlock(true),
      warlockCount
    );
  }

  private <T> void actionAtRandom(List<T> source, Consumer<T> action, int amount) {
    int sourceCount = source.size();
    int pickedIndex;

    while (sourceCount > 0 && amount > 0) {
      pickedIndex = (int) Math.floor(Math.random() * sourceCount);
      T pickedItem = source.get(pickedIndex);

      action.accept(pickedItem);

      source = source.stream()
        .filter(item -> item != pickedItem)
        .collect(Collectors.toList());
      
      amount--;
      sourceCount--;
    }
  }
}
