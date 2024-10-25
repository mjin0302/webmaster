package com.jin.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.jin.common.DataSource;
import com.jin.mapper.FullCalendarMapper;
import com.jin.vo.CalendarVO;

public class FullCalendarServiceImpl implements FullCalendarService {

	SqlSession session = DataSource.getInstance().openSession(true);
	FullCalendarMapper mapper = session.getMapper(FullCalendarMapper.class);
	
	@Override
	public List<Map<String, Object>> fullCalendarAllList() {
		return mapper.selectFullCalendar();
	}

	@Override
	public boolean addEvent(CalendarVO calendar) {
		return mapper.addEvent(calendar) == 1;
	}

	@Override
	public boolean removeEvent(CalendarVO calendar) {
		return mapper.deleteEvent(calendar) == 1;
	}

}
