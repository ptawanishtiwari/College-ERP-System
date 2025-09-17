package com.student.Portal.controller;

import com.student.Portal.entity.Attendence;
import com.student.Portal.entity.studentReg;
import com.student.Portal.entity.uploadmarks;
import com.student.Portal.repository.AttendenceRepository;
import com.student.Portal.repository.RegistrationRepository;
import com.student.Portal.repository.uploadmarksrepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AttendenceController {

    @Autowired
    private AttendenceRepository attendenceRepository;

    @Autowired
    private RegistrationRepository studentRepository;

    @Autowired
    private uploadmarksrepo uploadmarksrepos;

    @GetMapping("/attendance")
    public String showAttendanceForm(Model model) {
        List<studentReg> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "stuAttendence";
    }


  // Get all student details whose attendece is marked
//    @GetMapping("/uploadMarks")
//    public String marked(Model model){
//        List <studentReg> students = studentRepository.findAll();
//        model.addAttribute("students", students);
//        return "Teacher";
//    }

    @GetMapping("/MarkedAttendence")
    public String marked(HttpSession session, Model model) {
        studentReg student = (studentReg) session.getAttribute("student");

        if (student != null) {
            List<Attendence> studentAttendance = attendenceRepository.findByStudentId(student.getId());
            model.addAttribute("students", studentAttendance);
            return "StudentAttendence"; // sirf us student ka data dikhayega
        }

        return "redirect:/login"; // agar session expire ho gaya
    }



    @PostMapping("/submitAttendance")
    public String submitAttendance(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        String date = request.getParameter("date");
        String teacher = request.getParameter("teacher");

        List<studentReg> students = studentRepository.findAll();

        for (studentReg stu : students) {
            String status = request.getParameter("status_" + stu.getId());

            if(status != null && !status.equals("#")) {
                Attendence att = new Attendence(
                        stu.getId(),
                        stu.getName(),
                        status,
                        date,
                        teacher
                );
                attendenceRepository.save(att);
            }
        }
        redirectAttributes.addFlashAttribute("success", "Attendence Marked Successfully!");
        return "redirect:/attendance";
    }

    // submit marks

    @PostMapping("/submitMarks")
    public String submitMarks(HttpServletRequest request , RedirectAttributes redirectAttributes) {
        String total = request.getParameter("Total"); // total marks ek hi baar hoga

        List<studentReg> students = studentRepository.findAll(); // registered students

        for (studentReg stu : students) {
            String subject = request.getParameter("subject_" + stu.getId());
            String marks = request.getParameter("marks_" + stu.getId());

            // check if subject selected
            if (subject != null && !subject.equals("#") && marks != null && !marks.isEmpty()) {
                uploadmarks um = new uploadmarks(
                        stu.getId(),
                        stu.getName(),
                        subject,
                        marks,
                        total
                );
                uploadmarksrepos.save(um);
            }
        }
        redirectAttributes.addFlashAttribute("success", "Marks Uploaded Successfully!");
        return "redirect:/UploadMarks";
    }

    // All uploaded marks details

    @GetMapping("/StudentMarks")
    public String marks(HttpSession session, Model model) {
        studentReg student = (studentReg) session.getAttribute("student");

        if (student != null) {
            List<uploadmarks> studentAttendance = uploadmarksrepos.findByStudentId(student.getId());
            model.addAttribute("students", studentAttendance);
            return "StudentMarks"; // sirf us student ka data dikhayega
        }

        return "redirect:/stuDashboard"; // agar session expire ho gaya
    }

}

