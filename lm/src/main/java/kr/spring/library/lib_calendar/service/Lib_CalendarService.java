package kr.spring.library.lib_calendar.service;

import java.util.List;

import kr.spring.library.lib_calendar.vo.Lib_CalendarVO;

public interface Lib_CalendarService {
	// 일정 추가하기
	public void insertCalendarEvent(Lib_CalendarVO calendarVO);

	public List<Lib_CalendarVO> selectCalendarEvent();

	public Lib_CalendarVO selectScheduleDetail(Integer calendar_num);

	// 일정 수정
	public void updateCalendarEvent(Lib_CalendarVO calendarVO);

	// 일정 삭제
	public void deleteCalendarEvent(Integer calendar_num);

}
