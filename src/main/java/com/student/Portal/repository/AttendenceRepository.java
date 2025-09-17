package com.student.Portal.repository;

import com.student.Portal.entity.Attendence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendenceRepository extends JpaRepository<Attendence, Integer> {

    List<Attendence> findByStudentId(int studentId);
}

