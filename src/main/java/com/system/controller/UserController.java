package com.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.system.dto.UserDto;
import com.system.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService us;

	@RequestMapping(value = {"", "/", "/list" })
	public ModelAndView showUserList() {
		try {
			ModelAndView mav = new ModelAndView("user/list");
			// 리스트 가져오기
			List<UserDto> activeUserList = us.getActiveUserList();
			mav.addObject("list", activeUserList);
			return mav;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/add")
	public String showUserAddForm() {
		return "user/add";
	}
	
	@PostMapping("/add")
	public String addUser(UserDto userDto) {
		// 회원 추가
		try {
			us.addUser(userDto);
			return "redirect:/user";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/delete")
	public String deleteUser(@RequestParam("id") int id) {
		System.out.println("회원 삭제");
		System.out.println(id);
		// 회원 삭제
		try {
			us.deleteUser(id);
			return "redirect:/user";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
