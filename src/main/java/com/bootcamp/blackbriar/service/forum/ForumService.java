package com.bootcamp.blackbriar.service.forum;

import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.forum.ForumRequest;
import com.bootcamp.blackbriar.model.forum.ForumSettingsEntity;
import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.repository.ForumRepository;
import com.bootcamp.blackbriar.repository.ForumSettingsRepository;
import com.bootcamp.blackbriar.repository.GroupRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class ForumService {
  @Autowired
  ForumRepository forumRepository;

  @Autowired
  ForumSettingsRepository settingsRepository;

  @Autowired
  GroupRepository groupRepository;

  @Autowired
  ModelMapper modelMapper;

  public ForumEntity fetchForum(long forumId) {
    return forumRepository.findById(forumId)
      .orElseThrow(() -> new EntityNotFoundException("This forum activity does not exist."));
  }

  public ForumEntity createForum(long groupId, ForumRequest forumDetails, String instructorId) {
    GroupEntity group = groupRepository.findById(groupId)
      .orElseThrow(() -> new EntityNotFoundException(""));
    
    if (!group.getOwner().getUserId().equals(instructorId)) {
      throw new RuntimeException("Only group owners can add forum activities to them.");
    }

    ForumEntity forum = modelMapper.map(forumDetails, ForumEntity.class);
    ForumSettingsEntity settings;

    forum.setGroup(group);

    forum = forumRepository.save(forum);
    settings = modelMapper.map(forumDetails, ForumSettingsEntity.class);

    settings.setForum(forum);
    settingsRepository.save(settings);
    forum.setSettings(settings);

    return forum;
  }

  public void removeForum(long forumId, String instructorId) {
    ForumEntity toRemove = forumRepository.findById(forumId)
      .orElseThrow(() -> new EntityNotFoundException("This forum activity does not exist."));
    
    if (!toRemove.getGroup().getOwner().getUserId().equals(instructorId)) {
      throw new RuntimeException("You are not allowed to delete forums that you didn't create.");
    }

    forumRepository.delete(toRemove);
  }

  public List<ForumEntity> getForumsByGroup(long groupId, String userId) {
    GroupEntity group = groupRepository.findById(groupId)
      .orElseThrow(() -> new EntityNotFoundException("Group not found."));
    
    if (group.getOwner().getUserId().equals(userId)) {
      return forumRepository.findByGroupId(groupId);
    } else {
      return forumRepository.findByGroupIdAndPublished(groupId, true);
    }
  }
}

