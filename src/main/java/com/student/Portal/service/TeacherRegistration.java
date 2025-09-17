package com.student.Portal.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.student.Portal.entity.TeacherReg;
import com.student.Portal.repository.TeaRegRepo;

@Service
public class TeacherRegistration {
       
	@Autowired
	private TeaRegRepo regs;
	
	public void savedata(TeacherReg tr) {
		regs.save(tr);
}

	public TeacherReg findByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return regs.findByEmailAndPassword(email, password);
	}

	public List<TeacherReg> getAllReg() {
		// TODO Auto-generated method stub
		return regs.findAll();
		
	}
	
//	public List<TeacherReg> getAllReg(){
//		
//		return regs.findAll();
//		
//	}
//	
//	

}
