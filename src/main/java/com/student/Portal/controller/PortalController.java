package com.student.Portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.student.Portal.entity.AdminInfo;
import com.student.Portal.entity.TeacherReg;
import com.student.Portal.entity.studentReg;
import com.student.Portal.entity.uploadmarks;
import com.student.Portal.service.Adminservice;
import com.student.Portal.service.TeacherRegistration;
import com.student.Portal.service.marksservice;
import com.student.Portal.service.studentRegistration;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PortalController {

	// ====================== Services ======================
	@Autowired
	private studentRegistration service;

	@Autowired
	private TeacherRegistration trservice;

	@Autowired
	private Adminservice aservice;

	@Autowired
	private marksservice mark;


	// ====================== PUBLIC PAGES ======================
	@GetMapping("/")
	public String ShowHome() {
		return "index";
	}

	@GetMapping("/Registration")
	public String getRegistration() {
		return "Registration";
	}

	@GetMapping("/TeacherReg")
	public String getTeaRegistration() {
		return "TeacherReg";
	}

	@GetMapping("/OnlineTest")
	public String test(){
		return "OnlineTest";
	}

	@GetMapping("GenerateQuetions")
	public String Questions(){
		return "GenerateQuetions";
	}

//	@GetMapping("/UploadMarks")
//	public String getuploadMark() {
//		return "UploadMarks";
//	}

	@GetMapping("/uploadCopy")
	public String getuploadCopy() {
		return "uploadCopy";
	}

	@GetMapping("/stuAttendence")
	public String attendence(Model model) {
		List<studentReg> students = service.getAllReg(); // Service से सभी students लाओ
		model.addAttribute("students", students);
		return "stuAttendence"; // stuAttendence.html
	}

	@GetMapping("/UploadMarks")
	public String uploadattendence(Model model) {
		List<studentReg> students = service.getAllReg(); // Service से सभी students लाओ
		model.addAttribute("students", students);
		return "UploadMarks"; // stuAttendence.html
	}

	@GetMapping("/forgetPassword")
	public String Password() {
		return "forgetPassword";
	}

	// ====================== Profile details fetch data  ======================

    // Show a specific student profile details
	@GetMapping("/StudentProfile")
	public String getProfile(HttpSession session, Model model) {
		studentReg student = (studentReg) session.getAttribute("student");
		if (student != null) {
			model.addAttribute("student", student);
			return "StudentProfile"; // profile.html
		}
		return "redirect:/login";
	}

	@GetMapping("/TeacherProfile")
	public String getProfileTea(HttpSession session , Model model){
		TeacherReg teachers = (TeacherReg) session.getAttribute("teacher");
		if (teachers != null) {
			model.addAttribute("teacher", teachers);
			return "TeacherProfile"; // profile.html
		}
		return "Teacher";
	}



	// ====================== PASSWORD RESET ======================
	// search the email for reset password
	public studentReg findByEmail(String email) {
		return service.findByEmail(email);
	}

	// Step 3: Save new password
//	@PostMapping("/confirmsave")
//	public String confirmSave(@RequestParam("email") String email,
//							  @RequestParam("password") String password,
//							  @RequestParam("confirmpassword") String confirmPassword,
//							  Model model ) {
//
//		// Passwords check
//		if (!password.equals(confirmPassword)) {
//			model.addAttribute("error", "Passwords do not match!");
//			model.addAttribute("email", email);
//			return "forgetPassword"; // ✅ यही आपका page है
//		}
//
//		// Update password
//		service.updatePassword(email, password);
//
//		model.addAttribute("msg", "Password updated successfully!");
//		return "redirect:/forgetPassword"; // ✅ reset के बाद login page पर ले जाए
//	}




	@PostMapping("/confirmsave")
	public String confirmSave(@RequestParam("email") String email,
							  @RequestParam("password") String password,
							  @RequestParam("confirmpassword") String confirmPassword,
							  RedirectAttributes redirectAttributes,
							  Model model) {

		// ❌ अगर passwords match नहीं हुए → same page render
		studentReg student = service.findByEmail(email);
		if (student == null) {
			model.addAttribute("error", "Email not found!");
			return "forgetPassword";
		}
		if (!password.equals(confirmPassword)) {
			model.addAttribute("error", "Passwords do not match!");
			model.addAttribute("email", email);
			return "forgetPassword";  // same page dikhega
		}

		// ✅ Update password
		service.updatePassword(email, password);

		// ✅ Flash attribute add karo (redirect ke baad bhi milega)
		redirectAttributes.addFlashAttribute("success", "Password updated successfully!");

		// ✅ Redirect karo
		return "redirect:/forgetPassword";
	}


	// ====================== LOGIN PAGES ======================
	@GetMapping("/login")
	public String getLogin(Model model) {

		model.addAttribute("user", new studentReg());
		return "login";
	}

	@GetMapping("/adminlogin")
	public String getadminLogin(Model model) {
		model.addAttribute("admin", new AdminInfo());
		return "adminlogin";
	}

	@GetMapping("/tealogin")
	public String getteaLogin(Model model) {
		model.addAttribute("teacher", new TeacherReg());
		return "tealogin";
	}


	// ====================== LOGOUT CODE (TESTING + FINAL) ======================

	// Testing purpose logout (commented)
//    @GetMapping("/stulogout")
//    public String logout(HttpSession session) {
//        session.invalidate();  // ❌ purana login session destroy kar do
//        return "redirect:/login"; // ✅ wapas login page bhej do
//    }
//
//    @GetMapping("/adminlogout")
//    public String logoutadmin(HttpSession session) {
//        session.invalidate();  // ❌ purana login session destroy kar do
//        return "redirect:/adminlogin"; // ✅ wapas login page bhej do
//    }
//
//    // Teacher logout
//    @GetMapping("/tealogout")
//    public String logoutTeacher(HttpSession session) {
//        session.invalidate();
//        return "redirect:/tealogin"; // teacher login page
//    }

	// Session required before login

	// Student logout
//    @GetMapping("/stulogout")
//    public String logoutStudent(HttpSession session) {
//        session.invalidate();
//        return "redirect:/login";  // student login page
//    }
//
//    // Admin logout
//    @GetMapping("/adminlogout")
//    public String logoutAdmin(HttpSession session) {
//        session.invalidate();
//        return "redirect:/adminlogin"; // admin login page
//    }
//
//    // Teacher logout
//    @GetMapping("/tealogout")
//    public String logoutTeacher(HttpSession session) {
//        session.invalidate();
//        return "redirect:/tealogin"; // teacher login page
//    }

	// ✅ Single code to logout all roles
	@GetMapping("/logout")
	public String logoutuser(HttpSession session, RedirectAttributes redirectAttributes) {
		String role = (String) session.getAttribute("role");
		session.invalidate();
		redirectAttributes.addFlashAttribute("success", "You have Successfully logout!");
		if ("admin".equals(role)) {

			return "redirect:/adminlogin";
		} else if ("teacher".equals(role)) {

			return "redirect:/tealogin";
		} else {

			return "redirect:/login";  // default student
		}
	}


	// ====================== STUDENT REGISTRATION ======================
	@PostMapping("/save")
	public String addRegistration(@ModelAttribute studentReg r, RedirectAttributes redirectAttributes) {
		service.save(r);
		redirectAttributes.addFlashAttribute("success", "Registration Successful!");
		return "redirect:/Registration";
	}

	// after update redirect to student detailed page
	@PostMapping("/saverecord")
	public String saveistration(@ModelAttribute studentReg r ) {
		service.save(r);
		return "redirect:/stuDetails";
	}

	// Edit student Record
	@RequestMapping("/edit/{id}")
	public String editRecord(@PathVariable("id") int id, Model model,RedirectAttributes redirectAttributes) {
		studentReg b = service.getRecordById(id);
		redirectAttributes.addFlashAttribute("success", "Details Updated  Successful!");
		model.addAttribute("student", b);
		return "updateStd";
	}

	// Delete Student Record
	@RequestMapping("/delete/{id}")
	public String deleteListBook(@PathVariable("id") int id,RedirectAttributes redirectAttributes) {
		service.DeleteStudentById(id);
		redirectAttributes.addFlashAttribute("success", "Record Deleted Successful!");
		return "redirect:/stuDetails";
	}


	// ====================== TEACHER & MARKS REGISTRATION ======================
	@PostMapping("/saveteacher")
	public String addTeaRegistration(@ModelAttribute TeacherReg tr,RedirectAttributes redirectAttributes) {
		trservice.savedata(tr);
		redirectAttributes.addFlashAttribute("success", "Teacher Successfully Registered!");
		return "redirect:/TeacherReg";
	}

	@PostMapping("/savemarks")
	public String addMark(@ModelAttribute uploadmarks m,RedirectAttributes redirectAttributes) {
		mark.savedata(m);
		redirectAttributes.addFlashAttribute("success", "Registration Successful!");
		return "redirect:/UploadMarks";
	}


	// ====================== ADMIN PAGES ======================
//	@GetMapping("/Admin")
//	public ModelAndView getAdminStudentData() {
//		List<studentReg> list = service.getAllReg();
//		return new ModelAndView("Admin", "reg", list);
//	}

	@GetMapping("/stuDetails")
	public ModelAndView getAdminStudentDetails() {
		List<studentReg> list = service.getAllReg();
		return new ModelAndView("stuDetails", "reg", list);
	}

	@GetMapping("/TeaDetails")
	public ModelAndView getTeacherDetails() {
		List<TeacherReg> list1 = trservice.getAllReg();
		return new ModelAndView("TeaDetails", "tea", list1);
	}


	// ====================== DASHBOARD (Protected) ======================

	// Old: dashboard page show without login required
//    @GetMapping("stuDashboard")
//    public String getStudent() {
//        return "stuDashboard";
//    }

	// Must be login require to access student dashboard
	@GetMapping("/stuDashboard")
	public String getStudentDash(HttpSession session) {
		Object student = session.getAttribute("student");
		if (student == null) {
			return "redirect:/login";
		}
		return "stuDashboard";
	}

	@GetMapping("/Admin")
	public String getAdminDash(HttpSession session) {
		Object admin = session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/adminlogin";
		}
		return "Admin";
	}

	@GetMapping("Teacher")
	public String getTeacher(HttpSession session) {
		Object teacher = session.getAttribute("teacher");
		if (teacher == null) {
			// ❌ not logged in → redirect to login page
			return "redirect:/tealogin";
		}
		return "Teacher"; // ✅ logged in → show dashboard
	}



	// ====================== LOGIN PROCESS ======================

	// Student Login
	@PostMapping("/login")
	public String loginUserasStudent(@ModelAttribute("user") studentReg user, Model model, HttpSession session ,RedirectAttributes redirectAttributes) {

		// service se email aur password validate karna hoga
		studentReg existingUser = service.findByNameAndEmailAndPassword(user.getName(),
				user.getEmail(), user.getPassword());

		if (existingUser != null) {
			// ✅ Login successful
			session.setAttribute("student", existingUser); // Student data session me daala
			session.setAttribute("role", "student");       // Role bhi set kiya

			redirectAttributes.addFlashAttribute("success", "Login Successful!");
			return "redirect:/stuDashboard";
		} else {
			// Login failed
			model.addAttribute("error", "Invalid Email or Password!");
			return "login";
		}
	}

	// Admin Login
	@PostMapping("/adminlogin")
	public String loginUserasAdmin(@ModelAttribute("admin") AdminInfo admin, Model model, HttpSession session,RedirectAttributes redirectAttributes) {

		AdminInfo existingUser = aservice.findByEmailAndPassword(admin.getEmail(), admin.getPassword());

		if (existingUser != null) {
			session.setAttribute("admin", existingUser);
			session.setAttribute("role", "admin");

			redirectAttributes.addFlashAttribute("success", "Login Successful!");

			return "redirect:/Admin";
		} else {
			model.addAttribute("error", "Invalid Email or Password!");
			return "adminlogin";
		}
	}

	// Teacher Login
	@PostMapping("/tealogin")
	public String loginUserasTeacher(@ModelAttribute("teacher") TeacherReg teacher, Model model, HttpSession session,RedirectAttributes redirectAttributes) {

		TeacherReg existingUser = trservice.findByEmailAndPassword(teacher.getEmail(), teacher.getPassword());

		if (existingUser != null) {
			session.setAttribute("teacher", existingUser);
			session.setAttribute("role", "teacher");


			redirectAttributes.addFlashAttribute("success", "Login Successful!");
			return "redirect:/Teacher";
		} else {
			model.addAttribute("error", "Invalid Email or Password!");
			return "tealogin";
		}
	}
	// ====================== Upload and Fetch Book details and Student and teache end  ======================

	@GetMapping("/StudentBook")
	public String getBook(){
		return "StudentBook";
	}
	//============================== Student Attendence ===================

	@GetMapping("/StudentAttendence")
	public String Studentattendence(){
		return "StudentAttendence";
	}



}
