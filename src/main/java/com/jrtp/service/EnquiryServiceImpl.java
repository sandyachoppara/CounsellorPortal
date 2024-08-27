package com.jrtp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.jrtp.dto.ViewFilterRequest;
import com.jrtp.entity.Counselor;
import com.jrtp.entity.Enquiry;
import com.jrtp.repository.CounselorRepository;
import com.jrtp.repository.EnquiryRepository;

@Service
public class EnquiryServiceImpl implements EnquiryService{

	@Autowired
	EnquiryRepository enqRepo;
	
	@Autowired
	CounselorRepository counselorRepo; 
	
	
	@Override
	public boolean addEnquiry(Enquiry enquiry, Integer cid) throws Exception {
		Counselor counselor = counselorRepo.findById(cid).orElseThrow(null);
		if(counselor==null)
			throw new Exception("No Counselor Found");		
		enquiry.setCounselor(counselor);		
		Enquiry save = enqRepo.save(enquiry);
		if(save!=null)
			return true;
		else
			return false;

	}

	@Override
	public List<Enquiry> getEnquiries(Integer cid, ViewFilterRequest filterReq) throws Exception {
		
		Enquiry e= new Enquiry();
		
		Counselor c = counselorRepo.findById(cid).orElse(null);
		if(c==null)
			throw new Exception("No Counselor Found");
		e.setCounselor(c);
		
		if(filterReq.getCourse()!=null && !filterReq.getCourse().equals("")) {
			e.setCourse(filterReq.getCourse());
		}
		if(filterReq.getMode()!=null && !filterReq.getMode().equals("")) {
			e.setMode(filterReq.getMode());
		}
		if(filterReq.getStatus()!=null && !filterReq.getStatus().equals("")) {
			e.setStatus(filterReq.getStatus());
		}
		Example<Enquiry> of = Example.of(e);
		return enqRepo.findAll(of);

	}

	@Override
	public List<Enquiry> getAllEnquiries(Integer cid) {
		// TODO Auto-generated method stub
		Counselor c = counselorRepo.findById(cid).orElse(null);
		List<Enquiry> enquiries = c.getEnquiries();
		return enquiries;
	}

	@Override
	public Enquiry getEnquiryById(Integer eid) {
		// TODO Auto-generated method stub
		 Enquiry enquiry = enqRepo.findById(eid).orElseThrow(null);
		return enquiry;
	}
	
	

}
