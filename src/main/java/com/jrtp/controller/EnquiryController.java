package com.jrtp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jrtp.dto.ViewFilterRequest;
import com.jrtp.entity.Enquiry;
import com.jrtp.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//@Controller
public class EnquiryController {

	EnquiryService enqService;

	public EnquiryController(EnquiryService enqService) {
		this.enqService = enqService;
	}

	@GetMapping("/enquiry")
	public String showEnquiry(Model model) {
		Enquiry enquiry = new Enquiry();
		model.addAttribute("enquiry", enquiry);
		return "enquiry";
	}

	@PostMapping("/enquiry")
	public String handleEnquiry(Enquiry enquiry, HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession(false);
		Integer cId = (Integer) session.getAttribute("cid");
		boolean isSaved = enqService.addEnquiry(enquiry, cId);
		if (isSaved)
			model.addAttribute("smsg", "Enquiry Saved");
		else
			model.addAttribute("emsg", "Enquiry Not Saved.. Please try again");
		return "enquiry";
	}

	@GetMapping("/editenquiry/{eid}")
	public String editEnquiryShow(@PathVariable Integer eid,
			Model model) throws Exception {
		Enquiry enquiry = enqService.getEnquiryById(eid);
		model.addAttribute("enquiry", enquiry);
		return "enquiry";
	}

	@GetMapping("/viewenquiries")
	public String viewEnquiries(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession(false);
		Integer cId = (Integer) session.getAttribute("cid");
		List<Enquiry> allEnquiries = enqService.getAllEnquiries(cId);
		ViewFilterRequest viewFilterRequest= new ViewFilterRequest();
		model.addAttribute("viewFilterRequest", viewFilterRequest);
		model.addAttribute("enquiries", allEnquiries);
		return "viewenquiries";
	}
	@PostMapping("/viewfilterenquiries")
	public String getEnquiries(Integer cid, ViewFilterRequest viewFilterRequest,HttpServletRequest request, Model model) throws Exception
	{
		HttpSession session = request.getSession(false);
		Integer cId = (Integer) session.getAttribute("cid");
		List<Enquiry> allEnquiries = enqService.getEnquiries(cId, viewFilterRequest);
		model.addAttribute("enquiries", allEnquiries);
		return "viewenquiries";
		
	}
}
