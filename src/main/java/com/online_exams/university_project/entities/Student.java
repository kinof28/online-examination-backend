package com.online_exams.university_project.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.online_exams.university_project.base_entities.BaseClient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Student extends BaseClient{
	@OneToMany
	private Degree degree;
	@ManyToMany
	private List<Option> activeOptions;
//	private List<Notification> notifications;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("student");
        return Collections.singletonList(simpleGrantedAuthority);
	}

}
