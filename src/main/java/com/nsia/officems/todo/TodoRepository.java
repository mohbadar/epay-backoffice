package com.nsia.officems.todo;

import java.util.List;

import com.nsia.officems._identity.authentication.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>  {
    List<Todo> findByDone(Boolean done);
    List<Todo> findByArchived(Boolean archive);
    List<Todo> findByCreatedBy(User user);
}