package com.jin.dao;

import java.util.List;

import com.jin.vo.Member;

// interface 기능정의
// 구현 클래스 기능실행
public interface MemberMapper {
	// members 메소드를 구현하는 쿼리가 mapper.xml 파일에 존재함

	public List<Member> members(); 			// 전체조회

	public Member selectMember(String id); 	// 단건조회

	public int insertMember(Member member); // 멤버등록

	public int updateMember(Member member); // 멤버수정

	public int deleteMember(String memberId); 	// 멤버삭제
}
