package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository
public class BookDao {

	@Autowired
	JdbcTemplate jd;
	
	public List<Book> getMobileAllInfo(){
		List<Book> li=new ArrayList<Book>();
		jd.query("select bid,bname,author,publisher,price from book", new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				li.add(new Book(rs.getInt("bid"),rs.getString("bname"),rs.getString("author"),rs.getString("publisher"),rs.getInt("price")));	
			}
		});
		return li;
	}
	
	public int addItem(String name,String author,String publisher,int price) {
		String query="insert into book(bname,author,publisher,price) values(?,?,?,?)";
		return jd.update(query,name,author,publisher,price);
	}
	
	public int deleteItem(int id) {
		String query="delete from book where bid=?";
		return jd.update(query,id);
	}
	
	public int updateItem(int id,String name,String author,String publisher,int price) {
		String query="update book set bname=?,author=?,publisher=?,price=? where bid=?";
		return jd.update(query,name,author,publisher,price,id);
	}
}
