package com.employee.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.management.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByUserName(String userName);
	
	@Query(value="select u.* from user u inner join user_role ur on u.user_id = ur.user_id inner join role r on ur.role_id = r.role_id where r.role_name='ADMIN'",nativeQuery = true)
	List<User> findAdmin();
}
