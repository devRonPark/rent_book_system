package com.system.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class BookDto {
	private int id, writerId;
	private String title, category, publisher, summary;
	private int totalPage;
	private LocalDate publishedAt;
	private UserDto writer;
	private List<ThumbnailDto> thumbnails;
}
