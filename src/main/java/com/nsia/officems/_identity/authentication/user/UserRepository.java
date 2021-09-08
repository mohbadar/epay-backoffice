package com.nsia.officems._identity.authentication.user;

import java.util.List;

import javax.transaction.Transactional;

import com.nsia.officems._identity.authentication.group.Group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//JpaRepository<User, Integer> : User is the entity type and Integer is the primary key.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT new com.nsia.officems._identity.authentication.user.User(u.id, u.name, u.username, u.avatar, u.active) from User u where UPPER(u.name) like UPPER(?1)")
    List<User> searchByName(String term);

    @Query(value = "select user_tbl.id, user_tbl.name, user_tbl.username, user_tbl.avatar, user_tbl.active from user_tbl inner join user_group on user_group.user_id = user_tbl.id inner join group_tbl on group_tbl.id = user_group.group_id inner join group_role on group_role.group_id = user_group.group_id inner join role_permission on role_permission.role_id = group_role.role_id inner join permission on permission.id = role_permission.permission_id where permission.name=?1 group by user_tbl.id, user_tbl.name, user_tbl.username, user_tbl.avatar, user_tbl.active", nativeQuery = true)
    List<Object> findUsersByPermission(String permission);

    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User u set u.avatar = ?2 where u.username = ?1")
    int updateAvatar(String username, String avatar);

    @Query("SELECT new com.nsia.officems._identity.authentication.group.Group(g.id, g.name, g.description, g.active) from User u INNER JOIN u.groups g where u.id = ?1")
    List<Group> findAllGroupsByUser(Long userId, String envSlug);

    List<User> findByIdIn(List<Long> ids);

}
