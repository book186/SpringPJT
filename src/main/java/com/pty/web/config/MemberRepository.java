package com.pty.web.config;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pty.web.dto.member.MemberInfo;

@Repository
public interface MemberRepository extends JpaRepository<MemberInfo, Long>{

	@EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"memberAuthoritiesMappingList","memberAuthoritiesMappingList.memberAuthoritiesCode"})
	//	@Query(value = 
	//	"select m from MemberInfo m "
	//	+ 	"join fetch m.memberAuthoritiesMappingList a "
	//	+ 		"join fetch a.memberAuthoritiesCode "
	//	+ "where m.memberId = :memberId" )
	MemberInfo findByMemberId(String memberId);
}
