package kr.spring.library.rent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.product.BookProductService;
import kr.spring.library.product.vo.BookProductVO;
import kr.spring.library.rent.service.RentService;
import kr.spring.library.rent.vo.RentVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RentController {
	@Autowired
	private RentService rentService;
	@Autowired
	private BookProductService bookProductService;
	
	//대출 리스트
	@RequestMapping("/library/rent/rentHistoryList.do")
	public ModelAndView getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								String keyfield,String keyword,HttpSession session) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);		
		ModelAndView mav=new ModelAndView();
		log.debug("<<currentPage>> : "+currentPage);
		int count=rentService.selectRentRowCount(map);
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,
						currentPage,count,20,10,
						"/library/rent/rentHistoryList.do",null);		
		
		List<RentVO> list=null;
			if(count>0) {
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				
				list = rentService.selectRentHistory(map);				
			}
		mav.setViewName("rentList");
		mav.addObject("list",list);
		mav.addObject("page", page.getPage());
		mav.addObject("count", count);		
		log.debug("<<count>> : "+count);
		
		return mav;
	}
	
	/* ===============
	 *  대출 회원 검색
	 * =============== */
	@RequestMapping("/libarary/rent/memberSearchAjax.do")
	@ResponseBody
	public Map<String, Object> memberSearchAjax(@RequestParam String mem_id, HttpSession session){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		int user=0;
		user=(Integer)session.getAttribute("mem_num");
		if(user == 0) {
			//로그인 X
			mapJson.put("result", "logout");
		}else {
			//로그인 O
			List<MemberVO> member = rentService.selectSearchMember(mem_id);
			mapJson.put("result", "success");
			mapJson.put("member", member);
		}
		return mapJson;
	}
	
	/* ===============
	 *  대출 도서 검색
	 * =============== */
	@RequestMapping("/libarary/rent/bookSearchAjax.do")
	@ResponseBody
	public Map<String, Object> bookSearchAjax(@RequestParam String lib_product_bookname, HttpSession session){
		Map<String, Object> mapJson = new HashMap<String, Object>();
		int user=0;
		user=(Integer)session.getAttribute("mem_num");
		if(user == 0) {
			//로그인 X
			mapJson.put("result", "logout");
		}else {
			//로그인 O
			List<BookProductVO> book= bookProductService.searchDetailLIB_P(lib_product_bookname);
			mapJson.put("result", "success");
			mapJson.put("book", book);
		}
		return mapJson;
	}	
	
	/* ===============
	 *  대출 등록
	 * =============== */
	@RequestMapping("/library/rent/rentHistoryWrite.do")
	@ResponseBody
	public Map<String,Object> rentBook(RentVO vo, HttpSession session){
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		int user =0;
		user=(Integer)session.getAttribute("mem_num");
		if(user == 0) {
			//로그인 X인 경우
			mapJson.put("result", "logout");
		}else {
			//로그인 O인 경우
			
			//회원당 대출권수 확인
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("mem_num", vo.getMem_num());
			log.debug("<<mem_num>> : "+vo.getMem_num());
			int count=rentService.selectRentCountByMem_num(map);
			log.debug("<<rent_count>> : "+count);
			String[] list;
			list=vo.getCallNumber().split(",");
			log.debug("<<list_length>> : "+list.length);
			if((count+list.length)>3) {
				mapJson.put("result", "overValue");
			}else {
				log.debug("<<total>> : "+(count+list.length));
				for(String i:list) {
					vo.setCallNumber(i);
					rentService.insertRentHistory(vo);
					log.debug("<<vo>> : "+vo);
				}
				mapJson.put("result", "success");
			} 
			
		}
		
		return mapJson;
	}	
	
	//대출 반납
	@RequestMapping("/library/rent/updateRentHistory.do")
	public String returnBook(@RequestParam int rent_num,
            				HttpSession session,
							HttpServletRequest request,Model model){
		
		RentVO vo=rentService.selectRent(rent_num);
		rentService.updateRentHistory(vo);
		
		//view에 표시할 메시지
		model.addAttribute("message", "반납 완료!");
		model.addAttribute("url", "/library/rent/rentHistoryList.do");
		
		return "common/resultView";
	}
	
	
}
