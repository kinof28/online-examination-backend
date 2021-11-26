package com.online_exams.university_project.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class HelpMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String name;
    @Column(columnDefinition = "Text")
    private String subject;
    @Column(columnDefinition = "LongText")
    private String context;
}
