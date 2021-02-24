package com.safemtech.process.automation.srumtomation.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "rb_comment")
public class Comment {

	@Id
	@GeneratedValue
	private Long id;

	private String comment;

	@Enumerated(EnumType.STRING)
	private CommentType type;

	@CreatedDate
	private Timestamp createdDate;

	@CreatedBy
	private String createdBy;

}
