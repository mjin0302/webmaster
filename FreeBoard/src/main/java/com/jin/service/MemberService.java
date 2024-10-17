package com.jin.service;

import java.util.List;

import com.jin.vo.MemberVO;

// 업무 처리 로직
public interface MemberService {
	public boolean addMember(MemberVO member);

	public boolean retireMember(String id);
	
	// 회원목록 출력 메소드
	List<MemberVO> memberList();
}
