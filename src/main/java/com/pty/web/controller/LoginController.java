package com.pty.web.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pty.web.service.KaKaoAPI;

@Controller
public class LoginController {
	@Autowired
	private KaKaoAPI kakao;

	@RequestMapping(value = "/")
	public String index() {

		return "index";
	}

	@GetMapping("/securelogin")
	public String Login(HttpServletRequest request, HttpServletResponse response) {
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		try {
			// 여러가지 이유로 이전페이지 정보가 없는 경우가 있음.
			// https://stackoverflow.com/questions/6880659/in-what-cases-will-http-referer-be-empty
			request.getSession().setAttribute("prevPage", savedRequest.getRedirectUrl());
		} catch (NullPointerException e) {
			request.getSession().setAttribute("prevPage", "/");
		}
		return "index";
	}

	@GetMapping("/login")
	public String login(@RequestParam("code") String code, HttpSession session) {
		String access_Token = kakao.getAccessToken(code);
		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);

		System.out.println("login Controller : " + userInfo);

		if (userInfo.get("email") != null) {
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("access_Token", access_Token);
		}

		return "index";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		kakao.kakaoLogout((String) session.getAttribute("access_Token"));

		session.removeAttribute("access_Token");
		session.removeAttribute("userId");

		return "index";
	}
}