package com.student.Portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.Portal.entity.AdminInfo;


public interface Adminrepo extends JpaRepository<AdminInfo, Long>{
	
	AdminInfo findByEmailAndPassword(String email, String password);
	
	

}
