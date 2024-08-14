package com.system.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.system.dto.BookDto;
import com.system.dto.ThumbnailDto;
import com.system.mapper.BookMapper;
import com.system.mapper.ThumbnailMapper;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookMapper bm;
	
	@Autowired
	private ThumbnailMapper thm;
	
	@Override
	public List<BookDto> getBookList() throws Exception {
		return bm.findAll();
	}

	@Override
	public BookDto findById(int id) throws Exception {
		return bm.findById(id);
	}

	@Override
	public void addBook(BookDto book, List<MultipartFile> files) throws Exception {
		bm.insert(book);
		
		int bookId = book.getId();
		
		if (files != null && !files.isEmpty()) {
			List<ThumbnailDto> thumbnailList = new ArrayList<ThumbnailDto>();
			for (MultipartFile file: files) {
				ThumbnailDto thumbnail = new ThumbnailDto();
				String originalFilename = file.getOriginalFilename();
				String storedFilename = UUID.randomUUID() + "_" + originalFilename;
				String storedFilePath = "C:\\Users\\WD\\book_file\\" + storedFilename;
				long fileSize = file.getSize();
				
				thumbnail.setBookId(bookId);
				thumbnail.setOriginFileName(originalFilename);
				thumbnail.setStoredFilePath(storedFilePath);
				thumbnail.setFileSize(fileSize);
				
				thumbnailList.add(thumbnail);
				
				// 파일 저장
				try {
					File dest = new File(storedFilePath);
					file.transferTo(dest);
				} catch (IOException e) {
					throw new Exception("파일 업로드 중 오류가 발생했습니다.");
				}
			}
			thm.insert(thumbnailList);
		}
	}

	@Override
	public void deleteBook(int id) throws Exception {
		BookDto validBook = bm.findById(id);
		if (validBook == null) {
			throw new Exception("Invalid id");
		}
		// 소프트 딜리트 처리(is_deleted 값을 'y' 로 처리)
		// 궁금증? 도서 삭제할 때 이와 연관된 작가나 썸네일 이미지들은 어떻게 처리?
		bm.softDeleteById(id);
	}

}
