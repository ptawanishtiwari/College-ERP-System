package com.student.Portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;



import com.student.Portal.entity.TeacherReg;

public interface TeaRegRepo extends JpaRepository<TeacherReg, Long>{
	
	
	
	 TeacherReg findByEmailAndPassword(String email, String password);

}
