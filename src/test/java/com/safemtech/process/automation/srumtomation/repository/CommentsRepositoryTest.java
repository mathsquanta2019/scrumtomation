package com.safemtech.process.automation.srumtomation.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.safemtech.process.automation.srumtomation.entity.Comment;
import com.safemtech.process.automation.srumtomation.entity.CommentType;


@DataJpaTest
public class CommentsRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private CommentRepository commentRepository;

	@Test
	public void findCommentByDate() {

		// Given
		Comment comment = new Comment();
		comment.setComment("Comment 1");
		comment.setType(CommentType.PLUS);
		comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		testEntityManager.persistAndFlush(comment);

		// when

		LocalDate date = LocalDate.now();
		List<Comment> comments = commentRepository.findbyCreatedYearAndMonthAndDay(date.getYear(), date.getMonth().getValue(),
				date.getDayOfMonth());
		// Then
		assertThat(comments).hasSize(1);
		assertThat(comments.get(0)).hasFieldOrPropertyWithValue("type", CommentType.PLUS);
	}
}
