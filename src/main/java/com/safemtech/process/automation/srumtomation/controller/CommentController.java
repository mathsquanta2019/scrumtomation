package com.safemtech.process.automation.srumtomation.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.safemtech.process.automation.srumtomation.entity.Comment;
import com.safemtech.process.automation.srumtomation.entity.CommentType;
import com.safemtech.process.automation.srumtomation.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommentController {

	private final CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute("time",
				new SimpleDateFormat("yyyy-MM-dd HH:mm:SS").format(Calendar.getInstance().getTime()));
		List<Comment> comments = commentService.getAllCommentsForToday();

		Map<CommentType, List<Comment>> groupedComments = comments.stream()
				.collect(Collectors.groupingBy(Comment::getType));

		model.addAttribute("plusComments", groupedComments.get(CommentType.PLUS));
		model.addAttribute("deltaComments", groupedComments.get(CommentType.DELTA));
		model.addAttribute("starComments", groupedComments.get(CommentType.STAR));

		return "comment";
	}

	@PostMapping("/comment")
	public String createComment(@RequestParam(name = "plusComment", required = false) String plusComment,
			@RequestParam(name = "deltaComment", required = false) String deltaComment,
			@RequestParam(name = "starComment", required = false) String starComment, Model model) {

		List<Comment> comments = new ArrayList<>();

		if (StringUtils.isNotEmpty(plusComment)) {
			comments.add(getComment(plusComment, CommentType.PLUS));
		}

		if (StringUtils.isNotEmpty(deltaComment)) {
			comments.add(getComment(deltaComment, CommentType.DELTA));
		}

		if (StringUtils.isNotEmpty(starComment)) {
			comments.add(getComment(starComment, CommentType.STAR));
		}

		if (!comments.isEmpty()) {

			commentService.saveAll(comments);
			log.info("Comments saved successfully");
		}

		return "redirect:/";

	}

	private Comment getComment(String plusComment, CommentType plus) {
		Comment comment = new Comment();
		comment.setComment(plusComment);
		comment.setType(plus);
		return comment;
	}
}
