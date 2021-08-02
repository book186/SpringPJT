package com.pty.web.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pty.web.config.MemberRepository;
import com.pty.web.dto.member.MemberInfo;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	public MemberInfo findByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId);
	}
}
