package com.student.Portal.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.student.Portal.entity.studentReg;
import com.student.Portal.repository.RegistrationRepository;

import jakarta.transaction.Transactional;

@Service
public class studentRegistration {
	
	@Autowired
	private RegistrationRepository reg;
	
	// Save the Student Registration 
//	public void savedata(studentReg r) {
//
//		reg.save(r);
//	}
	
	// update details 
	public studentReg getRecordById(int id) {
		
		return reg.findAllById(id);
	}
	
	// show the list of Registered Student Details 
	public List<studentReg> getAllReg(){
		return reg.findAll();
		
	}
	
	// Login Student By this Details 
	  public studentReg findByNameAndEmailAndPassword( String name , String email, String password) {
	        return reg.findByNameAndEmailAndPassword(name,email, password);
	    }
	  
	  // Delete Perticular Student details By it . 
	  @Transactional
	public void DeleteStudentById(int id) {
		reg.deleteById(id);
		
	}

	public studentReg findByEmail(String email) {
		// TODO Auto-generated method stub
		return reg.findByEmail(email);
		
		
	}

	
	 @Transactional
	 public void updatePassword(String email, String newPassword) {
	     studentReg student = findByEmail(email);
	     if (student != null) {
	         student.setPassword(newPassword); // ✅ plain text save
	         reg.save(student); // repo.save(student)
	         
	     }
	 }


	public void save(studentReg r) {
		reg.save(r);
	}
}
