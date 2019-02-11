package com.spring.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.blog.model.Contents;

@Repository
public interface  ContentsRepository extends JpaRepository <Contents, Long> {
	
}

