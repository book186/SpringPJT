package com.pty.web.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.pty.web.dto.ResponseDataDTO;
import com.pty.web.dto.ResponseDataDTO.ResponseDataCode;
import com.pty.web.dto.ResponseDataDTO.ResponseDataStatus;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		ObjectMapper mapper = new ObjectMapper(); // JSON 변경용

		ResponseDataDTO responseDataDTO = new ResponseDataDTO();
		responseDataDTO.setCode(ResponseDataCode.ERROR);
		responseDataDTO.setStatus(ResponseDataStatus.ERROR);
		responseDataDTO.setMessage("아이디 혹은 비밀번호가 일치하지 않습니다.");

		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(mapper.writeValueAsString(responseDataDTO));
		response.getWriter().flush();

	}
}