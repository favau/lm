package kr.spring.intercepter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LibAdminCheckInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request,
			                 HttpServletResponse response,
			                 Object handler)throws Exception{
		log.debug("<<AdminCheckInterceptor 진입>>");
		
		//관리자가 로그인한 것인지 체크
		HttpSession session = request.getSession();
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");
		if(mem_auth == null || mem_auth != 9) {
			//관리자 권한이 아닐 때
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(
							 "/WEB-INF/views/common/notice_lib_admin.jsp");
			dispatcher.forward(request, response);
			return false;
		}
		return true;
	}
}





