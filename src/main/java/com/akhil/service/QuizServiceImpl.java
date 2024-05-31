package com.akhil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.akhil.fieng.IQuizInterface;
import com.akhil.model.Answers;
import com.akhil.model.Questions;
import com.akhil.model.QuestionsOnly;
import com.akhil.model.QuizTitles;
import com.akhil.model.Quizzes;
import com.akhil.repo.IQuizRepo;

@Service
public class QuizServiceImpl implements IQuizService {
	@Autowired
	private IQuizRepo repo;
	@Autowired
	private IQuizInterface feign;
	@Override
	public List<QuizTitles> fetchQuizByAdminId(Integer id) {
		List<Quizzes> quizzes=repo.findByAdminId(id);
		List<QuizTitles> quizTitleList=new ArrayList<>();
		for (Quizzes quiz:quizzes) {
			QuizTitles quizTitle=new QuizTitles(quiz.getQuizId(),quiz.getTitle());
			quizTitleList.add(quizTitle);	
		}
		return quizTitleList;
	}
	@Override
	public String savingQuiz(Quizzes quiz) {
		return "Quiz saved with title: "+repo.save(quiz).getTitle();
	}
	@Override
	public String createQuizByTopicAndNumQ(String topic, Integer numQ, String title,Integer adminId) {
		List<Integer> questionsList = feign.createQuiz(topic, numQ).getBody();
		Quizzes quiz=new Quizzes(questionsList,adminId,title);
		return "Quiz saved with title: "+repo.save(quiz).getTitle();
	}
	@Override
	public List<QuestionsOnly> getAllQuestions() {
		return feign.fetchingQuestionsOnly().getBody();
	}
	@Override
	public List<QuestionsOnly> getQuestionsByIds(List<Integer> questionIds) {
		return feign.fetchQuestionsByQuestionNums(questionIds).getBody();
	}
	@Override
	public List<QuestionsOnly> getQuestionByTopic(String topic) {
		return feign.fetchingQuestionsOnlyByTopic(topic).getBody();
	}
	
	@Override
	public String addQuestion(Questions question) {
		return feign.savingData(question).getBody();
	}
	@Override
	public List<Integer> getQuestionsByQuizId(Integer quizId) {
		return repo.findByQuizId(quizId);
	}
	@Override
	public Integer getScore(List<Answers> answer) {
		return feign.gettingScore(answer).getBody();
	}
	@Override
	public String createQuiz(Quizzes quiz) {
		return "Quiz saved with title: "+repo.save(quiz).getTitle();
	}
	@Override
	public String updateQuiz(Quizzes quiz) {
		Quizzes actualQuiz = repo.findById(quiz.getQuizId()).get();
		actualQuiz.setAdminId(quiz.getAdminId());
		actualQuiz.setQuestionIds(quiz.getQuestionIds());
		actualQuiz.setQuizId(quiz.getQuizId());
		actualQuiz.setTitle(quiz.getTitle());
		repo.save(actualQuiz);
		return "Quiz updated with title: "+actualQuiz.getTitle();
	}
	@Override
	public String deleteQuiz(Integer quizId) {
		repo.deleteById(quizId);
		return "Quiz deleted with Id:"+quizId;
	}
	@Override
	public String updateQuestion(Questions question) {
		return feign.updateQuestion(question).getBody();
	}
	@Override
	public String deleteQuestion(Integer id) {
		return feign.deleteQuestion(id).getBody();
	}
	@Override
	public Quizzes getQuizById(Integer quizId) {
		return repo.findById(quizId).get();
	}
	@Override
	public String savingQuiz() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toggleStatusOfQuiz(Integer quizId, Boolean status) {
		Optional<Quizzes> quizOptional = repo.findById(quizId);
		if(quizOptional.isPresent()) {
			Quizzes quiz = quizOptional.get();
			quiz.setStatus(status);
			repo.save(quiz);
			return "Status changed to: "+status;
		}
		return "Quiz not found";
	}
	@Override
	public List<QuizTitles> getAllActiveQuizzes() {
		return repo.findAllActiveQuizTitles();
	}

}
