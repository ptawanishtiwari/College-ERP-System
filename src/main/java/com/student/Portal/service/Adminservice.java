package com.student.Portal.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.student.Portal.entity.AdminInfo;
import com.student.Portal.repository.Adminrepo;

@Service
public class Adminservice {
        
	@Autowired
	private Adminrepo repo;
	
	public AdminInfo findByEmailAndPassword(String email, String password) {
		
		return repo.findByEmailAndPassword(email, password);
		
	}
	
	
}
