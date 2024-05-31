package com.akhil.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.akhil.model.QuizTitles;
import com.akhil.model.Quizzes;

public interface IQuizRepo extends JpaRepository<Quizzes, Integer> {
	List<Quizzes> findByAdminId(Integer id);
	@Query("SELECT q.questionIds FROM Quizzes q WHERE q.quizId = :quizId")
	List<Integer> findByQuizId(Integer quizId);
	List<Quizzes> findByStatus(Boolean status);
	@Query("SELECT new com.akhil.model.QuizTitles(q.quizId, q.title) FROM Quizzes q WHERE q.status = true")
    List<QuizTitles> findAllActiveQuizTitles();
}
