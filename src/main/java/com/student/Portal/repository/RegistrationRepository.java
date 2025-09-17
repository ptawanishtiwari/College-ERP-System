package com.student.Portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.student.Portal.entity.studentReg;

import javax.swing.text.html.Option;

@Repository
public interface RegistrationRepository extends JpaRepository<studentReg, Long> {
	
	studentReg findByNameAndEmailAndPassword(String name, String email, String password);

	studentReg findAllById(int id);
	
	

	void deleteById(int id);

	studentReg findByEmail(String email);






}
