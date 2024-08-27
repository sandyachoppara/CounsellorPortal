package com.jrtp.service;

import com.jrtp.dto.DashboardResponse;
import com.jrtp.entity.Counselor;

public interface CounselorService {
	
	String saveRegistration(Counselor C);
	Counselor checkLogin(String email, String pwd);
	DashboardResponse  getDashboardSummary(Integer  Cid);


}
