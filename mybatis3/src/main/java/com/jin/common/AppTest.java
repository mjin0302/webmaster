package com.jin.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jin.dao.MemberMapper;
import com.jin.vo.Member;

public class AppTest {
	public static void main(String[] args) {

		// memberDAO를 사용하기 위해 선언해줌
//		MemberDAO dao = new MemberDAO(); // jdbc형태

		// openSession()은 세션값을 받아오기 위한 메소드
		SqlSession sqlSession = DataSource.getInstance().openSession(); // 반환값이 SqlSession factory 이다

		// SqlSession이 가지고 있는 Mapper메소드
		// getMapper가 Interface파일과 xml 파일을 매핑한다 그래서 인터페이스와 xml 파일 이름이 같아야한다
		// MemberMapper.xml파일에 namespace와 id값과 똑같은 이름의 파일을 찾아서 매핑함
		MemberMapper dao = sqlSession.getMapper(MemberMapper.class);
		// 등록
		Member mbr = new Member();
		mbr.setMemberId("test99");
		mbr.setMemberName("연습99");
		mbr.setPhone("010-9999-9999");
		mbr.setPassword("999");
		
		//if(dao.insertMember(mbr) == 1) sqlSession.commit(); // commit메소드를 해줘야 디비에 실질적으로 반영이된다
		
		// 수정
		Member mbr2 = new Member();
		mbr2.setMemberId("test99");
		mbr2.setMemberName("킴옹심");
		mbr2.setPhone("010-8888-7777");
		
		if(dao.updateMember(mbr2) == 1) sqlSession.commit();
		
		// 삭제
		if(dao.deleteMember("test99") == 1) sqlSession.commit();;
		
		// 출력
		// dao클래스에 memberList() 메소드의 반환타입이 List라서 결과를 받는 List가 있어야함
		List<Member> result = dao.members();

		// List를 출력하기 위해  향샹된 for문을 이용해 출력
		for (Member member : result) {
			System.out.println(member);
		}
	}
}
