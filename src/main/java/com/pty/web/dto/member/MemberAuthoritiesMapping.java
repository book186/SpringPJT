package com.pty.web.dto.member;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_member_authorities_mapping")
@ToString
public class MemberAuthoritiesMapping {

	@Id
	@Column
	@GeneratedValue
	private long memberAuthoritiesMappingSeq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_info_seq", referencedColumnName = "memberInfoSeq")
	private MemberInfo memberInfo;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_authorities_code_seq", referencedColumnName = "memberAuthoritiesCodeSeq")
	private MemberAuthoritiesCode memberAuthoritiesCode;

	@Column
	@CreationTimestamp
	private LocalDate registerDate;

	public long getMemberAuthoritiesMappingSeq() {
		return memberAuthoritiesMappingSeq;
	}
	
	public void setMemberAuthoritiesMappingSeq(long memberAuthoritiesMappingSeq) {
		this.memberAuthoritiesMappingSeq = memberAuthoritiesMappingSeq;
	}
	
	public MemberInfo getMemberInfo() {
		return memberInfo;
	}
	
	public void setMemberInfo(MemberInfo memberInfo) {
		this.memberInfo = memberInfo;
	}
	
	public MemberAuthoritiesCode getMemberAuthoritiesCode() {
		return memberAuthoritiesCode;
	}
	
	public void setMemberAuthoritiesCode(MemberAuthoritiesCode memberAuthoritiesCode) {
		this.memberAuthoritiesCode = memberAuthoritiesCode;
	}
	
	public LocalDate getRegisterDate() {
		return registerDate;
	}
	
	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}
}
