package com.student.Portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.Portal.entity.uploadmarks;

import java.util.List;


public interface uploadmarksrepo extends JpaRepository<uploadmarks, Integer>{

    List<uploadmarks> findByStudentId(int studentId);
	
	

}
