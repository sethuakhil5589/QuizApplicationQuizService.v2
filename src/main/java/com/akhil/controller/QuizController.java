package com.akhil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhil.model.Answers;
import com.akhil.model.Questions;
import com.akhil.model.QuestionsOnly;
import com.akhil.model.QuizRandom;
import com.akhil.model.QuizTitles;
import com.akhil.model.Quizzes;
import com.akhil.service.IQuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	private IQuizService service;
	
	@GetMapping("/titles/{id}")	//fetches all titles with admin Id
	public ResponseEntity<List<QuizTitles>> getQuizTitles(@PathVariable Integer id){
		return new ResponseEntity<>(service.fetchQuizByAdminId(id),HttpStatus.FOUND);
	}
	@PostMapping("/createRandom")  //creates a random quiz based on topic and num of questions and admin Id
	public ResponseEntity<String> createQuizByTopicAndNumQ(@RequestBody QuizRandom quizRandom){
		return new ResponseEntity<>(service.createQuizByTopicAndNumQ(quizRandom.getTopic(),quizRandom.getNumQ(),
				quizRandom.getTitle(),quizRandom.getAdminId()),HttpStatus.CREATED);
	}
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody Quizzes quiz){
		return new ResponseEntity<>(service.createQuiz(quiz),HttpStatus.OK);
	}
	@GetMapping("/allQuestions")
	public ResponseEntity<List<QuestionsOnly>> fetchAllQuestions(){
		return new ResponseEntity<>(service.getAllQuestions(),HttpStatus.FOUND);
	}
	@GetMapping("/quizDetails/{quizId}")
	public ResponseEntity<Quizzes> getQuizById(@PathVariable Integer quizId){
		return new ResponseEntity<>(service.getQuizById(quizId),HttpStatus.OK);
	}
	@PostMapping("/questionsByIds") //fetches questions by Id
	public ResponseEntity<List<QuestionsOnly>> getQuestionsByIds(@RequestBody List<Integer> questionIds){
		return new ResponseEntity<>(service.getQuestionsByIds(questionIds),HttpStatus.OK);
	}
	@GetMapping("/questionsByTopic/{topic}") //fetches all the questions based on the topic
	public ResponseEntity<List<QuestionsOnly>> getQuestionsByTopic(@PathVariable String topic){
		return new ResponseEntity<>(service.getQuestionByTopic(topic),HttpStatus.FOUND);
	}
	@GetMapping("/activeQuizzes")
	public ResponseEntity<List<QuizTitles>> activeQuizzes(){
		return new ResponseEntity<>(service.getAllActiveQuizzes(),HttpStatus.OK);
	}
	@GetMapping("/status/{quizId}/{status}")
	public ResponseEntity<String> changeStatusOfQuiz(@PathVariable Integer quizId, @PathVariable Boolean status){
		return new ResponseEntity<>(service.toggleStatusOfQuiz(quizId,status),HttpStatus.OK);
	}
	@PostMapping("/addQuestion") // Adds question
	public ResponseEntity<String> addQuestion(@RequestBody Questions question){
		return new ResponseEntity<>(service.addQuestion(question),HttpStatus.CREATED);
	}
	@GetMapping("/getQuiz/{quizId}")
	public ResponseEntity<List<Integer>> fetchQuestionsByQuizId(@PathVariable Integer quizId){
		return new ResponseEntity<>(service.getQuestionsByQuizId(quizId),HttpStatus.FOUND);
	}
	@PostMapping("/submit")
	public ResponseEntity<Integer> getScore(@RequestBody List<Answers> answers){
		return new ResponseEntity<>(service.getScore(answers),HttpStatus.OK);
	}
	@PutMapping("/updateQuiz")
	public ResponseEntity<String> updateQuiz(@RequestBody Quizzes quiz){
		return new ResponseEntity<>(service.updateQuiz(quiz),HttpStatus.OK);
	}
	@DeleteMapping("/deleteQuiz/{quizId}")
	public ResponseEntity<String> deleteQuiz(@PathVariable Integer quizId){
		return new ResponseEntity<>(service.deleteQuiz(quizId),HttpStatus.OK);
	}
	@PutMapping("/updateQuestion")
	public ResponseEntity<String> updateQuiz(@RequestBody Questions question){
		return new ResponseEntity<>(service.updateQuestion(question),HttpStatus.OK);
	}
	@DeleteMapping("/deleteQuestion/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
		return new ResponseEntity<>(service.deleteQuestion(id),HttpStatus.OK);
	}
}
