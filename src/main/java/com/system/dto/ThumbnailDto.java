package com.system.dto;

import lombok.Data;

@Data
public class ThumbnailDto {
	private int id, bookId;
	private String originFileName, storedFilePath;
	private long fileSize;
}
