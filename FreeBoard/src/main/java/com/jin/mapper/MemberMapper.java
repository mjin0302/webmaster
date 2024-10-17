package com.jin.mapper;

import java.util.List;

import com.jin.vo.MemberVO;



// interface 기능정의
// 구현 클래스 기능실행
public interface MemberMapper {
	// members 메소드를 구현하는 쿼리가 mapper.xml 파일에 존재함

	public List<MemberVO> members(); 			// 전체조회

	public MemberVO selectMember(String id); 	// 단건조회

	public int insertMember(MemberVO member); // 멤버등록

	public int updateMember(MemberVO member); // 멤버수정

	public int deleteMember(String memberId); 	// 멤버삭제
	
	
}
