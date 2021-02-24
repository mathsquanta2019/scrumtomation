package com.safemtech.process.automation.srumtomation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.safemtech.process.automation.srumtomation.entity.Comment;
import com.safemtech.process.automation.srumtomation.entity.CommentType;
import com.safemtech.process.automation.srumtomation.repository.CommentRepository;

public class CommentServiceTest {

	private CommentRepository commentRepository = Mockito.mock(CommentRepository.class);

	private CommentService commentService;
	List<Comment> comments = new ArrayList<>();

	@BeforeEach
	public void init() {
		commentService = new CommentService(commentRepository);
	}

	@Test
	public void commentServiceTest() {

		// Given

		Comment comment = new Comment();
		comment.setComment("comment");
		comment.setCreatedBy("sam");
		comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		comment.setType(CommentType.STAR);
		comments.add(comment);

		LocalDate currentDate = LocalDate.now();
		when(commentRepository.findbyCreatedYearAndMonthAndDay(currentDate.getYear(), currentDate.getMonthValue(),
				currentDate.getDayOfMonth())).thenReturn(comments);

		// when
		List<Comment> commentsForToday = commentService.getAllCommentsForToday();

		// Then

		verify(commentRepository, times(1)).findbyCreatedYearAndMonthAndDay(currentDate.getYear(),
				currentDate.getMonthValue(), currentDate.getDayOfMonth());
		assertThat(comments.equals(commentsForToday));
	}

	@Test
	public void testSaveAllComments() {
		// given
		Comment comment = new Comment();
		comment.setComment("comment2");
		comment.setCreatedBy("Ashis");
		comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		comment.setType(CommentType.DELTA);
		comments.add(comment);

		when(commentRepository.saveAll(comments)).thenReturn(comments);

		// when
		List<Comment> savedComments = commentService.saveAll(comments);

		// Then
		assertThat(savedComments.equals(comments));

		verify(commentRepository, times(1)).saveAll(comments);

	}
}
