package com.jrtp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jrtp.dto.DashboardResponse;
import com.jrtp.entity.Counselor;
import com.jrtp.service.CounselorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//@Controller
public class CounselorController {

	CounselorService counselorService;
	
	public CounselorController(CounselorService counselorService) {
		this.counselorService = counselorService;
	}

	@GetMapping("/")
	public String showLogin(Model model) {
		model.addAttribute("counselor", new Counselor());
		return "login";
	}

	@PostMapping("/login")
	public String handleLogin(Counselor counselor, Model model, HttpServletRequest req) {
		Counselor counsellor = counselorService.checkLogin(counselor.getEmail(), counselor.getPassword());
		
		if(counsellor==null) {
			model.addAttribute("msg", "Invalid Email or Password");
			return"login";
		}
		HttpSession session=req.getSession(true);
		session.setAttribute("cid", counsellor.getId());
		return "redirect:dashboard";
	}

	@GetMapping("/registration")
	public String showRegister(Model model) {
		model.addAttribute("c", new Counselor());
		return "registration";
	}
	
	@PostMapping("/registration")
	public String handleRegister(@ModelAttribute("c") Counselor c, Model model) {		
		String status = counselorService.saveRegistration(c);
		model.addAttribute("status", status);
		return "registration";
	}
	
	@GetMapping("/dashboard")
	public String showDashboard(Model model, HttpServletRequest req) {
		HttpSession session=req.getSession(false);
		Integer cid=(Integer) session.getAttribute("cid");
		DashboardResponse dashboardSummary = counselorService.getDashboardSummary(cid);
		model.addAttribute("di", dashboardSummary);		
		return "dashboard";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session=req.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
}
