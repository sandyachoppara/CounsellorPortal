package com.jrtp.controller;

import java.util.List;

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

import com.jrtp.dto.ViewFilterRequest;
import com.jrtp.entity.Enquiry;
import com.jrtp.service.EnquiryService;

@RestController
@RequestMapping("counsellor")
@CrossOrigin
public class EnquiryRestController {
	@Autowired
	EnquiryService enquiryService;
	
	@PostMapping("/enquiry")
	ResponseEntity<String> saveEnquiry(@RequestBody Enquiry e, Integer cid) throws Exception {
		boolean status = enquiryService.addEnquiry(e, cid);
		if(status)
			return new ResponseEntity<String>("Enquiry Saved Successfully", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Enquiry couldnot saved", HttpStatus.BAD_REQUEST);
			
	}
	
	@PostMapping("/viewfilterenquiry")
	ResponseEntity<List<Enquiry>> getEnquiries(@RequestBody ViewFilterRequest filterReq,
			Integer cid) throws Exception {		
		List<Enquiry> enquiries = enquiryService.getEnquiries(cid, filterReq);
		return new ResponseEntity<List<Enquiry>>(enquiries, HttpStatus.OK);
	
	}
	@GetMapping("/viewenquiry")
	ResponseEntity<List<Enquiry>> getAllEnquiries(@RequestParam Integer cid) throws Exception {{
		List<Enquiry> enquiries = enquiryService.getAllEnquiries(cid);
		return new ResponseEntity<List<Enquiry>>(enquiries, HttpStatus.OK);
	}
	}
	
	
	
}
