package com.bootcamp.blackbriar.repository;

import com.bootcamp.blackbriar.model.comments.FeedbackEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends CrudRepository<FeedbackEntity, Long> { }
