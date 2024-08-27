package com.jrtp.service;

import java.util.List;

import com.jrtp.dto.ViewFilterRequest;
import com.jrtp.entity.Enquiry;

public interface EnquiryService {
	
	boolean addEnquiry(Enquiry e, Integer cid) throws Exception;
	List<Enquiry> getAllEnquiries(Integer cid)  throws Exception;
	List<Enquiry> getEnquiries(Integer cid, ViewFilterRequest filterReq)  throws Exception;
	Enquiry getEnquiryById(Integer eid);


}
