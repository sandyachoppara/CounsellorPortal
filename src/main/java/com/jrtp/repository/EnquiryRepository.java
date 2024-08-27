package com.jrtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrtp.entity.Enquiry;

public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {
	

}
