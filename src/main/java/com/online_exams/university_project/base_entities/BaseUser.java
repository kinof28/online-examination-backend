package com.online_exams.university_project.base_entities;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseUser implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false,unique = true,updatable = false)
	private String email;
	@Column(nullable = false,updatable = true,unique = false)
	private String password;
	@Column(nullable = false,name = "first_name")
	private String firstName;
	@Column(nullable = false,name="last_name")
	private String lastName;
	@Column(nullable = false,name="is_activated")
	private boolean isActivated=false;
	@Column(nullable = false,name="isLocked")
	private boolean isLocked=false;
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return !this.isLocked;
	}
	@Override
	public boolean isAccountNonLocked() {
		return !this.isLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return this.isActivated;
	}

}
