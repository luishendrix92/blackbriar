package com.bootcamp.blackbriar.repository;

import com.bootcamp.blackbriar.model.forum.ForumEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepository extends CrudRepository<ForumEntity, Long> {
  List<ForumEntity> findByGroupId(long groupId);

  List<ForumEntity> findByGroupIdAndPublished(long groupId, boolean isPublished);
}
