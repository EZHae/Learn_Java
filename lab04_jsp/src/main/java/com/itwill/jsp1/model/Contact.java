package com.itwill.jsp1.model;

public class Contact {
	private Integer id;
	private String name;
	private String phone;
	private String email;
	
	public Contact () {}
	public Contact(Integer id, String name, String phone, String email) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + "]";
	}
	
}
