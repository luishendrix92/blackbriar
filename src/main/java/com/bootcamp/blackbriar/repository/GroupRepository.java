package com.bootcamp.blackbriar.repository;

import com.bootcamp.blackbriar.model.group.GroupEntity;
import com.bootcamp.blackbriar.model.group.StudentGroupResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
  List<GroupEntity> findByOwnerUserId(String fk_user);

  @Query("SELECT new com.bootcamp.blackbriar.model.group.StudentGroupResponse(g.id, g.title, g.description, g.image, g.owner, g.publicGroup, m.active) FROM Membership m JOIN m.group g WHERE m.student.userId = ?1")
  List<StudentGroupResponse> findByStudentUserId(String userId);
}
