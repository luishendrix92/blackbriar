package com.bootcamp.blackbriar.repository.forum;

import com.bootcamp.blackbriar.model.forum.ForumSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("repositoryForumSettings")
public interface ForumSettingsRepository extends JpaRepository<ForumSettingsEntity, Serializable> {

    ForumSettingsEntity findById(long id);

}
