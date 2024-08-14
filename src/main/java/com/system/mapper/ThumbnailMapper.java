package com.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.system.dto.ThumbnailDto;

@Mapper
public interface ThumbnailMapper {
	public void insert(List<ThumbnailDto> thumbnails) throws Exception;
}
