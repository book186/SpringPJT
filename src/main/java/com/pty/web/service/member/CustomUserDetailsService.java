package com.pty.web.service.member;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pty.web.dto.member.MemberAuthoritiesMapping;
import com.pty.web.dto.member.MemberInfo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberService memberService;

	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		MemberInfo memberInfo = memberService.findByMemberId(memberId);
		return new User(memberInfo.getMemberId(), memberInfo.getMemberPassword(), getAuthorities(memberInfo));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(MemberInfo memberInfo) {
		String[] userRoles = convert(memberInfo.getMemberAuthoritiesMappingList());
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}

	public String[] convert(List<MemberAuthoritiesMapping> list) {
		String[] arrayOfString = new String[list.size()];

		int index = 0;
		for (MemberAuthoritiesMapping memberAuthoritiesMapping : list) {
			arrayOfString[index++] = memberAuthoritiesMapping.getMemberAuthoritiesCode().getAuthority();
		}
		return arrayOfString;
	}

}
