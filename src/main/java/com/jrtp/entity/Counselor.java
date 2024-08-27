package com.jrtp.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;


@Entity
public class Counselor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private String email;
	private String password;
	@CreationTimestamp
	@Column(name = "creation_date", updatable = false)
	private LocalDateTime creationDate;
	@UpdateTimestamp
	@Column(name = "update_date", insertable = false)
	private LocalDateTime updateDate;
	
	@JsonIgnore
	@JoinColumn(name = "counselor_id")
	@OneToMany
	List<Enquiry> enquiries;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	public List<Enquiry> getEnquiries() {
		return enquiries;
	}
	public void setEnquiries(List<Enquiry> enquiries) {
		this.enquiries = enquiries;
	}
	@Override
	public String toString() {
		return "Counselor [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", creationDate=" + creationDate + ", updateDate=" + updateDate + ", enquiries=" + enquiries + "]";
	}
	
	

}
