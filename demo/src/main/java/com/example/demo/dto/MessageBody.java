package com.example.demo.dto;

import java.io.Serializable;

public class MessageBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private String name;
	private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Name: ").append(this.name);
		sb.append("Address: ").append(this.address);
		
		return sb.toString();
	}
	
}
