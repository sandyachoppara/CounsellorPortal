package com.jrtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrtp.entity.Counselor;

public interface CounselorRepository extends JpaRepository<Counselor, Integer> {
	
	Counselor findByEmail(String email);
	Counselor findByEmailAndPassword(String email, String pwd);

}
