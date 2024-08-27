package com.jrtp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jrtp.dto.DashboardResponse;
import com.jrtp.entity.Counselor;
import com.jrtp.service.CounselorService;

@RestController
@CrossOrigin
public class CounselorRestController {

	@Autowired
	CounselorService counselorService;
	
	@PostMapping("/register")
	ResponseEntity<String> saveRegistration(@RequestBody Counselor c) {
		String saveRegistration = counselorService.saveRegistration(c);
		return new ResponseEntity<String>(saveRegistration, HttpStatus.OK);
	}
	@GetMapping("/login")
	ResponseEntity<Counselor> login(@RequestParam String email,@RequestParam String password){
		Counselor councelor = counselorService.checkLogin(email, password);
		return new ResponseEntity<Counselor>(councelor, HttpStatus.OK);
	}
	
	@GetMapping("/dashboard")
	ResponseEntity<DashboardResponse> getDashboardSummary(@RequestParam Integer cid) {
		
		DashboardResponse dashboardSummary = counselorService.getDashboardSummary(cid);
		return new ResponseEntity<DashboardResponse>(dashboardSummary, HttpStatus.OK);
		
	}
	}
	

