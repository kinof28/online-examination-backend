package com.online_exams.university_project.entities;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.online_exams.university_project.base_entities.BaseUser;

@Entity
public class Admin extends BaseUser{

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("admin");
        return Collections.singletonList(simpleGrantedAuthority);
	}

}
