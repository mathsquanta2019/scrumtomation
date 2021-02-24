package com.safemtech.process.automation.srumtomation.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safemtech.process.automation.srumtomation.entity.Comment;
import com.safemtech.process.automation.srumtomation.repository.CommentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@Slf4j
public class CommentService {

	private final CommentRepository commentRepository;

	@Autowired
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Transactional(rollbackFor = Exception.class)
	public List<Comment> saveAll(List<Comment> comment) {

		log.info("Sucessfully saved comments. Transaction id:{}", UUID.randomUUID().toString());

		return commentRepository.saveAll(comment);

	}

	public List<Comment> getAllCommentsForToday() {
		LocalDate today = LocalDate.now();

		log.info("Sucessfully retrieved comment. Transaction id:{}", UUID.randomUUID().toString());
		return commentRepository.findbyCreatedYearAndMonthAndDay(today.getYear(), today.getMonthValue(),
				today.getDayOfMonth());
	}

}
