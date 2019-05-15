/**
 * User: Alexis M. Gutierrez Kinto
 * Date: 13/05/19
 */

package com.bootcamp.blackbriar.repository.forum;

import com.bootcamp.blackbriar.model.forum.ForumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

// Repository - se encarga de hacer peticiones a la base de datos
@Repository("repository")
public interface ForumRepository extends JpaRepository<ForumEntity, Serializable>{

//    public abstract List<ForumEntity> findByTitle(String title);

//    ForumEntity findByTitle(String title);

    ForumEntity findById(long id);
}
