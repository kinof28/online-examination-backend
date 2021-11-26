package com.online_exams.university_project.base_entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseConfirmationToken {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , updatable = false , unique = true )
    private String token;
	public BaseConfirmationToken(String token) {
		this.token = token;
	}
    

}
