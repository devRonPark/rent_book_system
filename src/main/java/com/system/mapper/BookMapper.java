package com.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.system.dto.BookDto;
import com.system.dto.UserDto;

@Mapper
public interface BookMapper {
	public List<BookDto> findAll() throws Exception;
	public BookDto findById(int id);
	public void insert(BookDto book);
	public void softDeleteById(int id);
}
