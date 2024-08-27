package com.jrtp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrtp.dto.DashboardResponse;
import com.jrtp.entity.Counselor;
import com.jrtp.entity.Enquiry;
import com.jrtp.repository.CounselorRepository;
import com.jrtp.repository.EnquiryRepository;

@Service
public class CouncelorServiceImpl implements CounselorService{
	
	@Autowired
	CounselorRepository counselorRepo; 
	
	@Autowired
	EnquiryRepository enquiryRepo;

	@Override
	public String saveRegistration(Counselor c) {
		Counselor counselor = counselorRepo.findByEmail(c.getEmail());

		if (counselor != null) {
			return "Email already registered. Please proceed to Login";
		} else {
			Counselor sc = counselorRepo.save(c);
			if (sc == null) {
				return "Registration not done";
			}else {
			return "Registration done. Please proceed to Login";
			}
		}

	}

	@Override
	public Counselor checkLogin(String email, String pwd) {
		Counselor counselor = counselorRepo.findByEmailAndPassword(email, pwd);
		return counselor;
	}

	@Override
	public DashboardResponse getDashboardSummary(Integer cid) {
		Counselor counselor = counselorRepo.findById(cid).orElseThrow(null);		
		List<Enquiry> enquiries = counselor.getEnquiries();
		DashboardResponse dr= new DashboardResponse();
		dr.setTotal(enquiries.size());
		dr.setEnrolled((int)enquiries.stream().filter(e->e.getStatus().equalsIgnoreCase("Enrolled")).count());
		dr.setLost((int)enquiries.stream().filter(e->e.getStatus().equalsIgnoreCase("Lost")).count());
		dr.setOpen((int)enquiries.stream().filter(e->e.getStatus().equalsIgnoreCase("Open")).count());
			
		return dr;
	}

}
