package com.system.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.system.dto.BookDto;

public interface BookService {
	public List<BookDto> getBookList() throws Exception;
	public BookDto findById(int id) throws Exception; 
	public void addBook(BookDto book, List<MultipartFile> files) throws Exception;
	public void deleteBook(int id) throws Exception;
}
