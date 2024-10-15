package com.jin.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jin.common.DAO;
import com.jin.vo.Member;

public class MemberDAO extends DAO {
	// 회원목록
	public List<Member> memberList() {
		List<Member> list = new ArrayList<>();
		
		getOpen();
		
		try {
			psmt = conn.prepareStatement("select * from tbl_member");
			rs = psmt.executeQuery();	// rs = set 컬렉션이다
			
			while(rs.next()) {
				Member member = new Member();
				
				member.setMemberId(rs.getString("member_id"));
				member.setMemberName(rs.getString("member_name"));
				member.setPhone(rs.getString("phone"));
				member.setResponsibility(rs.getString("responsibility"));
				
				list.add(member);
				
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return list;
		
	}
}
