package com.jin.mapper;

import java.util.List;
import java.util.Map;

import com.jin.vo.CalendarVO;

public interface FullCalendarMapper {

	List<Map<String, Object>> selectFullCalendar();

	int addEvent(CalendarVO calendar);

	int deleteEvent(CalendarVO calendar);	
	
	
}
