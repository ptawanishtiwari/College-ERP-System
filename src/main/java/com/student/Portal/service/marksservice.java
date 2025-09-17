package com.student.Portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.Portal.entity.uploadmarks;
import com.student.Portal.repository.uploadmarksrepo;

@Service
public class marksservice {
	
	@Autowired
	private uploadmarksrepo mark;
	
	public void savedata(uploadmarks m) {
		mark.save(m);
	}

}
