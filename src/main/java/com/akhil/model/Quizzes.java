package com.akhil.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
public class Quizzes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer quizId;
	@ElementCollection
	private List<Integer> questionIds;
	private Integer adminId;
	private String title;
	private boolean status = false; 
	public Quizzes(List<Integer> questionIds, Integer adminId, String title) {
		super();
		this.questionIds = questionIds;
		this.adminId = adminId;
		this.title = title;
		this.status = false;
	}
	
}
