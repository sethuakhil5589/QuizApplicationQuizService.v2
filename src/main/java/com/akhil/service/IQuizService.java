package com.akhil.service;

import java.util.List;

import com.akhil.model.Answers;
import com.akhil.model.Questions;
import com.akhil.model.QuestionsOnly;
import com.akhil.model.QuizTitles;
import com.akhil.model.Quizzes;

public interface IQuizService {
	List<QuizTitles> fetchQuizByAdminId(Integer id);
	String savingQuiz(Quizzes quiz);
	String createQuizByTopicAndNumQ(String topic, Integer numQ, String title,Integer adminId);
	List<QuestionsOnly> getAllQuestions();
	List<QuestionsOnly> getQuestionsByIds(List<Integer> questionIds);
	List<QuestionsOnly> getQuestionByTopic(String topic);
	String savingQuiz();
	String addQuestion(Questions question);
	List<Integer> getQuestionsByQuizId(Integer quizId);
	Integer getScore(List<Answers> answer);
	String createQuiz(Quizzes quiz);
	String updateQuiz(Quizzes quiz);
	String deleteQuiz(Integer quizId);
	String updateQuestion(Questions question);
	String deleteQuestion(Integer id);
	Quizzes getQuizById(Integer quizId);
	String toggleStatusOfQuiz(Integer quizId,Boolean status);
	List<QuizTitles> getAllActiveQuizzes();
}
