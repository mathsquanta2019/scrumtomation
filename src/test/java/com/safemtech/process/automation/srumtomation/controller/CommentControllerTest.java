package com.safemtech.process.automation.srumtomation.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.safemtech.process.automation.srumtomation.service.CommentService;

@WebMvcTest(CommentController.class)
@ContextConfiguration(classes = CommentService.class)
public class CommentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CommentService commentService;

	/*
	 * @Test public void saveComments() throws Exception {
	 * 
	 * // When ResultActions resultAction =
	 * mockMvc.perform(post("/comment").with(csrf()).with(user("user").roles("USER")
	 * ) .param("starComment", "some comment").param("plusComment",
	 * "another comment")); // Then
	 * resultAction.andExpect(status().is3xxRedirection()).andDo(
	 * MockMvcResultHandlers.print()) .andExpect(redirectedUrl(("/")));
	 * verify(commentService, times(1)).saveAll(Mockito.anyList());
	 * verifyNoMoreInteractions(commentService); }
	 */
}
