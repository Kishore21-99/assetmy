package com.sprint1.assets.repository;



import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint1.assets.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
@Query("select u.userPassword from User u where u.userId=?1")
public String getPassword( int userId);
}