package com.bootcamp.blackbriar.repository;

import com.bootcamp.blackbriar.model.answer.AnswerEntity;

import org.jboss.logging.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {

  
  AnswerEntity findByStudentId(long fk_membership);


}
