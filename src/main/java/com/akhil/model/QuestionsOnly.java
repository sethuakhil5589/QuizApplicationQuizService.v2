package com.akhil.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionsOnly {
	private Integer questionId;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
}
