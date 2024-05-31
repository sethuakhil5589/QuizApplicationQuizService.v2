package com.akhil.fieng;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.akhil.model.Answers;
import com.akhil.model.Questions;
import com.akhil.model.QuestionsOnly;

@FeignClient("QuizApplicationQuestionService.v2")
public interface IQuizInterface {
	@GetMapping("/questions/allQuestions") //fetches all Questions
	public ResponseEntity<List<QuestionsOnly>> fetchingQuestionsOnly();
	
	@PostMapping("/questions/create/{topic}/{numQ}") //generates quiz based on num of questions and topic in random format
	public ResponseEntity<List<Integer>> createQuiz(@PathVariable String topic, @PathVariable Integer numQ);
	
	@PostMapping("/questions/fetchByQNums") //fetches questions based on question numbers
	public ResponseEntity<List<QuestionsOnly>> fetchQuestionsByQuestionNums(@RequestBody List<Integer> questionNumbers);
	
	@PostMapping("/questions/submit") //gets score
	public ResponseEntity<Integer> gettingScore(@RequestBody List<Answers> answers);
	
	@GetMapping("/questions/getQuestions/{topic}") //fetches questions based on topic
	public ResponseEntity<List<QuestionsOnly>> fetchingQuestionsOnlyByTopic(@PathVariable String topic);
	
	@PostMapping("/questions/addQuestion")
	public ResponseEntity<String> savingData(@RequestBody Questions question);
	
	@PutMapping("/questions/updateQuestion")
	public ResponseEntity<String> updateQuestion(@RequestBody Questions question);
	
	@DeleteMapping("/questions/deleteQuestion/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer id);
}
