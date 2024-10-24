package com.jin.service;

import java.util.List;
import java.util.Map;

import com.jin.vo.CalendarVO;

public interface FullCalendarService {
	
	List<Map<String, Object>> fullCalendarAllList();
	
	boolean addEvent(CalendarVO calendar);
}
