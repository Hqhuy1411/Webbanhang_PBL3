package com.DemoSpring.DTO;

import java.sql.Date;



public class UserDTO {
	private int id;
	private String username;
	private String fullname;
	private boolean gender;
	private Date birthday;
	private String phone;
	private String email;
	private String address;
	private String password;
	private String confirm_password;
	
	private int id_city;
	
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId_city() {
		return id_city;
	}
	public void setId_city(int id_city) {
		this.id_city = id_city;
	}
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", fullname=" + fullname + ", gender=" + gender
				+ ", birthday=" + birthday + ", phone=" + phone + ", email=" + email + ", address=" + address
				+ ", password=" + password + ", confirm_password=" + confirm_password + ", id_city=" + id_city + "]";
	}
	
	
}
