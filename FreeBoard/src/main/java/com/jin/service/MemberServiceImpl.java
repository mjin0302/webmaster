package com.jin.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jin.common.DataSource;
import com.jin.mapper.MemberMapper;
import com.jin.vo.MemberVO;


public class MemberServiceImpl implements MemberService {
	SqlSession session = DataSource.getInstance().openSession(true);
	MemberMapper mapper = session.getMapper(MemberMapper.class);

	@Override
	public boolean addMember(MemberVO member) {
		return mapper.insertMember(member) == 1;
	}

	@Override
	public boolean retireMember(String id) {
		return mapper.deleteMember(id) == 1;
	}

	// 회원목록 출력 메소드
	@Override
	public List<MemberVO> memberList() {
		return mapper.members();
	}

	// 로그인 서비스
	@Override
	public MemberVO loginCheck(String id, String pw) {
		return mapper.loginMember(id, pw);
	}
}
