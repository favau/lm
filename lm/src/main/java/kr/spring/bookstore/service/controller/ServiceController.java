package kr.spring.bookstore.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.bookstore.service.service.ServiceService;
import kr.spring.bookstore.service.vo.AnnounceVO;
import kr.spring.bookstore.service.vo.FaqVO;
import kr.spring.library.board_announce.controller.BoardAnnounceController;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ServiceController {

	@Autowired
	private ServiceService serviceService;

	@ModelAttribute
	public AnnounceVO initCommand() {
		return new AnnounceVO();
	}
	@ModelAttribute
	public FaqVO initCommand2() {
		return new FaqVO();
	}

	@RequestMapping("/bookstore/service/main.do")
	public String serviceMain() {
		return "service";
	}
	@RequestMapping("/bookstore/service/announceList.do")
	public ModelAndView selectAnnounce(@RequestParam(value="pageNum",
		      defaultValue="1") int currentPage,
		@RequestParam(value="order",
		      defaultValue="1") int order) {

		Map<String,Object> map = 
				new HashMap<String,Object>();
		
		int count = serviceService.selectRowCount(map);
		
		log.debug("<<count>> : " + count);
		PagingUtil page = 
				new PagingUtil(
						currentPage,count,20,10,
						"list.do","&order="+order);
		
		List<AnnounceVO> list = null;
		if(count > 0) {
			map.put("order",order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = serviceService.selectAnnounceList(map);			
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("announceList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		return mav;
	}
	@GetMapping("/bookstore/service/announceWrite.do")
	public String formAnnounce() {
		return "announceWrite";
	}
	
	/*========================
	 * 게시판 글상세
	 *========================*/
	@RequestMapping("/bookstore/service/announceDetail.do")
	public ModelAndView getAnnounceDetail(@RequestParam int board_num) {
		log.debug("<<글상세 - board_num>> : " + board_num);
		
		//해당 글의 조회수 증가
		//serviceService.updateHit(board_num);
		
		//글상세 
		AnnounceVO board = serviceService.selectAnnounce(board_num);
		
		//제목에 태그를 허용하지 않음
		board.setBoard_title(StringUtil.useNoHtml(board.getBoard_title()));
		
		//CKEditor를 사용하지 않을 경우 내용에 태그 불허
		//board.setContent(StringUtil.useBrNoHtml(board.getContent()));
		                         //뷰 이름    속성명   속성값
		return new ModelAndView("announceView","board",board);
	}
	@PostMapping("/bookstore/service/announceWrite.do")
	public String writeAnnounce(@Valid AnnounceVO announceVO,
								BindingResult result,
								HttpSession session,
								HttpServletRequest request,
								Model model) {
		log.debug("<<글 작성>> : " + announceVO);

		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return formAnnounce();
		}
		//글 작성
		if(announceVO.getBoard_filename() == null) {
			announceVO.setBoard_filename("");
		}
		serviceService.insertAnnounce(announceVO);
		model.addAttribute("message", "글쓰기가 완료되었습니다.");
		model.addAttribute("url", 
				request.getContextPath()+"/bookstore/service/announceList.do");
		
		return "common/resultView";
	}
	/*========================
	 * 자주묻는질문
	 *========================*/
	@GetMapping("/bookstore/service/faqWrite.do")
	public String formFaq() {
		return "faqWrite";
	}
	@PostMapping("/bookstore/service/faqWrite.do")
	public String writeFaq(@Valid FaqVO faqVO,
								BindingResult result,
								HttpSession session,
								HttpServletRequest request,
								Model model) {
		log.debug("<<글 작성>> : " + faqVO);

		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return formFaq();
		}
		//글 작성
		
		serviceService.insertFaq(faqVO);
		model.addAttribute("message", "글쓰기가 완료되었습니다.");
		model.addAttribute("url", 
				request.getContextPath()+"/bookstore/service/faqList.do");
		
		return "common/resultView";
	}
}