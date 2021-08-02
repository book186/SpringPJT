package com.pty.web.dto.member;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.ToString;

@Entity
@Table(name = "t_member_info")
@ToString
public class MemberInfo {

	@Id
	@Column
	@GeneratedValue
	private long memberInfoSeq;

	@Column(nullable = false)
	private String memberId;

	@Column(nullable = false)
	private String memberEmail;

	@Column(nullable = false)
	private String memberPassword;

	@Column
	@CreationTimestamp
	private LocalDateTime registerDate;

	@Column
	@UpdateTimestamp
	private LocalDateTime updateDate;

	@Column
	private LocalDateTime deleteDate;

	@OneToMany(mappedBy = "memberInfo", fetch = FetchType.LAZY)
	private List<MemberAuthoritiesMapping> memberAuthoritiesMappingList;
	
	public long getMemberInfoSeq() {
		return memberInfoSeq;
	}
	
	public void setMemberInfoSeq(long memberInfoSeq) {
		this.memberInfoSeq = memberInfoSeq;
	}
	
	public String getMemberId() {
		return memberId;
	}
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String getMemberEmail() {
		return memberEmail;
	}
	
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	public String getMemberPassword() {
		return memberPassword;
	}
	
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	
	public LocalDateTime getRegisterDate() {
		return registerDate;
	}
	
	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}
	
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
	public LocalDateTime getDeleteDate() {
		return deleteDate;
	}
	
	public void setDeleteDate(LocalDateTime deleteDate) {
		this.deleteDate = deleteDate;
	}
	
	public List<MemberAuthoritiesMapping> getMemberAuthoritiesMappingList() {
		return memberAuthoritiesMappingList;
	}
	
	public void setMemberAuthoritiesMappingList(List<MemberAuthoritiesMapping> memberAuthoritiesMappingList) {
		this.memberAuthoritiesMappingList = memberAuthoritiesMappingList;
	}
}
