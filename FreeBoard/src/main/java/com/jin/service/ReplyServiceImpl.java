package com.jin.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jin.common.DataSource;
import com.jin.mapper.ReplyMapper;
import com.jin.vo.ReplyVO;

public class ReplyServiceImpl implements ReplyService {

	SqlSession session = DataSource.getInstance().openSession(true);
	ReplyMapper mapper = session.getMapper(ReplyMapper.class);
	
	@Override	// 댓글 조회
	public List<ReplyVO> replyList(int boardNo) {
		return mapper.selectList(boardNo);
	}

	@Override	// 댓글 등록
	public boolean addReply(ReplyVO reply) {
		return mapper.insertReply(reply) == 1;
	}

	@Override	// 댓글 삭제
	public boolean removeReply(int replyNo) {
		return mapper.deleteReply(replyNo) == 1;
	}

	@Override	// 단건조회
	public ReplyVO getReply(int replyNo) {
		return mapper.selectReply(replyNo);
	}


}
