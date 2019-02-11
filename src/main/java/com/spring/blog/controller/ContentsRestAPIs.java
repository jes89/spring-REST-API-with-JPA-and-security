package com.spring.blog.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog.model.Contents;
import com.spring.blog.repository.ContentsRepository;

@RestController
@RequestMapping("/blog")
public class ContentsRestAPIs {
	
	@Autowired
	private ContentsRepository contentsRepository;
	
	@PostMapping("/insert")
//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> insertContents(@RequestBody Contents contents) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date now = Calendar.getInstance().getTime();
		contents.setRegDtm(simpleDateFormat.format(now));
		contentsRepository.save(contents);
		return ResponseEntity.ok("success");
	}
	
	@GetMapping("/contentsList")
	public ResponseEntity<?> getContentsList() {
		return ResponseEntity.ok(contentsRepository.findAll());
	}
	
	@GetMapping("/contentsView")
	public ResponseEntity<?> getContentsView(@RequestBody long contIdx) {
		
		Contents contents =	contentsRepository.getOne(contIdx);
		
		if(contents == null) {
		   return new ResponseEntity<String>("Fail -> contIdx is not vaild!", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok("success");
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateContents(@RequestBody long contIdx ,@RequestBody String title, @RequestBody String contents){
		
		Contents selectedContents =	contentsRepository.getOne(contIdx);
		
		if(selectedContents == null) {
		   return new ResponseEntity<String>("Fail -> contIdx is not vaild!", HttpStatus.BAD_REQUEST);
		}
		
		selectedContents.setTitle(title);
		selectedContents.setContents(contents);
		
		contentsRepository.save(selectedContents);
		
		return ResponseEntity.ok("success");
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteContents(@RequestBody long contIdx){
		contentsRepository.deleteById(contIdx);
		return ResponseEntity.ok("success");
	}
}