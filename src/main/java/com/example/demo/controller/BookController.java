package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.BookDao;
import com.example.demo.model.Book;

@Controller
public class BookController {

	@Autowired
	BookDao bd;
	
	@RequestMapping("/")
	public String show() {
		return "index";
	}
	
	@RequestMapping(value="/all")
	public ResponseEntity<List<Book>> getAllItems(){
		return new ResponseEntity<>(bd.getMobileAllInfo(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public ResponseEntity<Object> addDetails(@RequestBody Book b){
		String msg=null;
		if(bd.addItem(b.getName(),b.getAuthor(),b.getPublisher(),b.getPrice())>=1) {
			msg="Data Saved";
		}else {
			msg="Please Check";
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteDetails(@PathVariable("id") int bid){
		String msg=null;
		if(bd.deleteItem(bid)>=1) {
			msg="Data Deleted";
		}else {
			msg="Please Check";
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateDetails(@PathVariable("id") int bid,@RequestBody Book b){
		String msg=null;
		if(bd.updateItem(bid, b.getName(),b.getAuthor(),b.getPublisher(),b.getPrice())>=1) {
			msg="Data Updated";
		}else {
			msg="Please Check";
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
}
