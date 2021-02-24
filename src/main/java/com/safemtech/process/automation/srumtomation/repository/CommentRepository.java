package com.safemtech.process.automation.srumtomation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.safemtech.process.automation.srumtomation.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query("SELECT c FROM Comment c where year(c.createdDate) = ?1 AND month(c.createdDate) = ?2 AND day(c.createdDate) = ?3")
	List<Comment> findbyCreatedYearAndMonthAndDay(int year, int month, int day);
}
