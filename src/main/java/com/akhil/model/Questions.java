package com.akhil.model;

import lombok.Data;
@Data
public class Questions {
	private Integer questionId;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	private String topic;
}
