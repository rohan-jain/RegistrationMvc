package com.company.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Required;

import com.company.validator.UserNameConstraint;
import com.company.validator.ValidUserName;
import com.sun.istack.NotNull;


@Entity
@Table(name="users", uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
//@ValidUserName
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
//	@Column(unique = true)
	@Size(min = 4,message = "atleast 4 characters")
//	@UserNameConstraint
	private String username;
	@Size(min = 7,message = "atleast 7 characters")
	private String email;
	@Size(min=5,message = "atleast 5 characters")
	private String address;
	@Pattern(regexp = "^\\d{10}$",message = "only 10 digits")
	private String mobileno;
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@&_-]).{6,18}",message = "atleast mixed case, number, one of #$@&_-, 6 chars")
	private String password;
	private String role;
	
	@ManyToOne
	private Organization organization;
	
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
