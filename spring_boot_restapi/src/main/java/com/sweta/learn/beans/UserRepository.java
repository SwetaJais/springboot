package com.sweta.learn.beans;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u  where name like %?1%")
	List<User> findByName(String name);
	
	@Modifying
	@Query("delete from User u where u.id=:id")
	public void hardDelete(@Param("id") long id);
		// TODO Auto-generated method stub
		


}
