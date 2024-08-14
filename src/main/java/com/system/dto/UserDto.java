package com.system.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {
	private int id;
	private String name, email;
	private LocalDateTime createdAt;
}
