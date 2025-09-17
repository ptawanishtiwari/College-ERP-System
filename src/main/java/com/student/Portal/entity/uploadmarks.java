package com.student.Portal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class uploadmarks {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int studentId;
	private String studentName;
	private String Subject;
	private String AchivedMarks;
	private String Total;

	public uploadmarks() {}

	public uploadmarks(int studentId, String studentName, String Subject, String AchivedMarks, String Total) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.Subject = Subject;
		this.AchivedMarks = AchivedMarks;
		this.Total = Total;
	}

	// getters and setters
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getStudentId() { return studentId; }
	public void setStudentId(int studentId) { this.studentId = studentId; }

	public String getStudentName() { return studentName; }
	public void setStudentName(String studentName) { this.studentName = studentName; }


	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getAchivedMarks() {
		return AchivedMarks;
	}

	public void setAchivedMarks(String achivedMarks) {
		AchivedMarks = achivedMarks;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}
}
