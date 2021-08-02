package com.pty.web.dto.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.ToString;

@Entity
@Table(name = "T_MEMBER_AUTHORITIES_CODE")
@ToString
public class MemberAuthoritiesCode {

	@Id
	@Column
	@GeneratedValue
	private long memberAuthoritiesCodeSeq;

	@Column(nullable = false)
	private String authority;

	@Column
	@CreationTimestamp
	private LocalDateTime registerDate;

	@OneToMany(mappedBy = "memberAuthoritiesCode", fetch = FetchType.LAZY)
	private List<MemberAuthoritiesMapping> memberAuthoritiesMappingList = new ArrayList<>();

	public long getMemberAuthoritiesCodeSeq() {
		return memberAuthoritiesCodeSeq;
	}
	
	public void setMemberAuthoritiesCodeSeq(long memberAuthoritiesCodeSeq) {
		this.memberAuthoritiesCodeSeq = memberAuthoritiesCodeSeq;
	}
	
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public LocalDateTime getRegisterDate() {
		return registerDate;
	}
	
	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}
	
	public List<MemberAuthoritiesMapping> getMemberAuthoritiesMappingList() {
		return memberAuthoritiesMappingList;
	}
	
	public void setMemberAuthoritiesMappingList(List<MemberAuthoritiesMapping> memberAuthoritiesMappingList) {
		this.memberAuthoritiesMappingList = memberAuthoritiesMappingList;
	}
}
