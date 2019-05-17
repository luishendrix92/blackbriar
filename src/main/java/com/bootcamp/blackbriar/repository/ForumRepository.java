package com.bootcamp.blackbriar.repository;

import com.bootcamp.blackbriar.model.forum.ForumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("repository")
public interface ForumRepository extends JpaRepository<ForumEntity, Serializable>{

//    public abstract List<ForumEntity> findByTitle(String title);

//    ForumEntity findByTitle(String title);

    ForumEntity findById(long id);
}
