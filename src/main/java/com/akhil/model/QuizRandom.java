package com.akhil.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizRandom {
	private Integer adminId;
	private String title;
	private String topic;
	private Integer numQ;
}
