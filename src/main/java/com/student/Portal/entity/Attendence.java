package com.student.Portal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Attendence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int studentId;
    private String studentName;
    private String attendence;
    private String attendenceDate;
    private String teacherName;

    public Attendence() {}

    public Attendence(int studentId, String studentName, String attendence, String attendenceDate, String teacherName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.attendence = attendence;
        this.attendenceDate = attendenceDate;
        this.teacherName = teacherName;
    }

    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getAttendence() { return attendence; }
    public void setAttendence(String attendence) { this.attendence = attendence; }

    public String getAttendenceDate() { return attendenceDate; }
    public void setAttendenceDate(String attendenceDate) { this.attendenceDate = attendenceDate; }

    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }
}
