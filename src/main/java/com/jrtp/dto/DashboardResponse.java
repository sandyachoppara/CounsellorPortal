package com.jrtp.dto;

public class DashboardResponse {
	
	Integer total;
	Integer open;
	Integer lost;
	Integer enrolled;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getOpen() {
		return open;
	}
	public void setOpen(Integer open) {
		this.open = open;
	}
	public Integer getLost() {
		return lost;
	}
	public void setLost(Integer lost) {
		this.lost = lost;
	}
	public Integer getEnrolled() {
		return enrolled;
	}
	public void setEnrolled(Integer enrolled) {
		this.enrolled = enrolled;
	}
	@Override
	public String toString() {
		return "DashboardResponse [total=" + total + ", open=" + open + ", lost=" + lost + ", enrolled=" + enrolled
				+ "]";
	}
	
	

}
