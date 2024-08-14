package com.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.system.dto.BookDto;
import com.system.dto.UserDto;
import com.system.service.BookService;
import com.system.service.UserService;

import jakarta.servlet.annotation.MultipartConfig;

@Controller
@RequestMapping("/book")
@MultipartConfig(maxFileSize = 2024 * 2024 * 5)
public class BookController {
	@Autowired
	private UserService us;
	@Autowired
	private BookService bs;
	
	@RequestMapping(value = {"", "/", "/list"})
	public ModelAndView showBookList() {
		try {
			ModelAndView mav = new ModelAndView("book/list");
			// 리스트 가져오기
			List<BookDto> bookList = bs.getBookList();
			
			for (BookDto book: bookList) {
				System.out.println(book.getThumbnails());
			}
			mav.addObject("list", bookList);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/detail")
	public ModelAndView showBookDetail(@RequestParam("id") int id) {
		try {
			ModelAndView mav = new ModelAndView("/book/detail");
			BookDto book = bs.findById(id);
			System.out.println(book.getThumbnails());
			mav.addObject("book", book);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/add")
	public ModelAndView showBookAddForm() {
		ModelAndView mav = new ModelAndView("book/add");
		List<UserDto> writerList;
		try {
			writerList = us.getActiveUserList();
			mav.addObject("writerList", writerList);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/add")
	public String addBook(BookDto bookDto, @RequestPart("files") List<MultipartFile> files) {
		System.out.println(bookDto);
		System.out.println(files);
		// 책 추가
		try {
			bs.addBook(bookDto, files);
			return "redirect:/book";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/delete")
	public String deleteBook(@RequestParam("id") int id) {
		// 책 삭제
		System.out.println("책 삭제 요청 들어옴");
		try {
			bs.deleteBook(id);
			return "redirect:/book";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/edit")
	public ModelAndView showBookEditForm(@RequestParam("id") int id) {
		try {
			ModelAndView mav = new ModelAndView("book/add");
			BookDto book = bs.findById(id);
			mav.addObject("book", book);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
