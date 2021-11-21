package com.online_exams.university_project.entities;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.online_exams.university_project.base_entities.BaseClient;

@Entity
public class Teacher extends BaseClient{

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("teacher");
        return Collections.singletonList(simpleGrantedAuthority);
	}

}
