package kr.spring.mypage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.library.board_announce.vo.BoardAnnounceVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.mypage.service.MyPageService;
import kr.spring.mypage.vo.MyPageVO;
import kr.spring.util.AuthCheckException;
import kr.spring.util.EncryptionPasswd;
import kr.spring.util.SaltGenerate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyPageController {
	
	@Autowired
	private MyPageService mypageService;
	
	/*=======================
	 * 자바빈 초기화
	 *=======================*/
	@ModelAttribute
	public MyPageVO initCommand() {
		return new MyPageVO();
	}
	
	/*=======================
	 * 마이페이지
	 *=======================*/
	@RequestMapping("/lm/mypage/main/myPageMain.do")
	public String formMyPage(HttpServletRequest request,Model model,@RequestParam int lo) {
		HttpSession session = request.getSession();
		//로그인 여부 체크
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		//로그인 안되어있을 시 로그인 화면으로 이동
		if(mem_num==null) {
			model.addAttribute("accessMsg", "먼저 로그인을 해주세요.");
			if(lo==1) {
				return "common/notice_bs";
			}else {
				return "common/notice_lib";
			}
		}
		return "myPageMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/main/myPageMain.do")
	public String myPage(@Valid MyPageVO mypage,
			@RequestParam int lo,BindingResult result,
			Model model,HttpServletRequest request,MyPageVO mypageVO) {
		log.debug("<<마이페이지>> : " + mypageVO);
		//intercepter에서 마이페이지 공용 데이터 처리하여 SELECT SQL 실행 후 반환. AppConfig.java,MyPageHeaderInterceptor.java
		//이후 주문내역 데이터 SELECT 하여 model에 addAttribute
		//페이지 추가 시 appconfig에 추가 해줘야 함
		
		//model을 이용한 파라미터 세팅
		//model.addAttribute("",null);
		

		return "myPageMain";
	}
	
	/*=======================
	 * 문의내역
	 *=======================*/
	@RequestMapping("/lm/mypage/asklist/askListMain.do")
	public String askList() {
		return "askListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/asklist/askListMain.do")
	public String askListHandle(@RequestParam int lo) {

		return "askListMain";
	}
	/*=======================
	 * 대출/반납내역
	 *=======================*/
	@RequestMapping("/lm/mypage/checkoutreturnlist/checkOutReturnListMain.do")
	public String checkOutReturnList() {
		return "checkOutReturnListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/checkoutreturnlist/checkOutReturnListMain.do")
	public String checkOutReturnListHandle(@RequestParam int lo) {

		return "checkOutReturnListMain";
	}
	/*=======================
	 * 희망도서신청내역
	 *=======================*/
	@RequestMapping("/lm/mypage/wantbooklist/wantBookListMain.do")
	public String wantBookList() {
		return "wantBookListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/wantbooklist/wantBookListMain.do")
	public String wantBookListHandle(@RequestParam int lo) {

		return "wantBookListMain";
	}
	/*=======================
	 * 프로그램신청내역
	 *=======================*/
	@RequestMapping("/lm/mypage/programapplylist/programApplyListMain.do")
	public String programApplyList() {
		return "programApplyListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/programapplylist/programApplyListMain.do")
	public String programApplyListHandle(@RequestParam int lo) {

		return "programApplyListMain";
	}
	/*=======================
	 * 책기증신청내역
	 *=======================*/
	@RequestMapping("/lm/mypage/donatebooklist/donateBookListMain.do")
	public String donateBookList() {
		return "donateBookListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/donatebooklist/donateBookListMain.do")
	public String donateBookListHandle(@RequestParam int lo) {

		return "donateBookListMain";
	}
	/*=======================
	 * 시설이용신청내역
	 *=======================*/
	@RequestMapping("/lm/mypage/facilityapplylist/facilityApplyListMain.do")
	public String facilityApplyList() {
		return "facilityApplyListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/facilityapplylist/facilityApplyListMain.do")
	public String facilityApplyListHandle(@RequestParam int lo) {

		return "facilityApplyListMain";
	}
	/*=======================
	 * 책예약내역
	 *=======================*/
	@RequestMapping("/lm/mypage/bookreservationlist/bookReservationListMain.do")
	public String bookReservationList() {
		return "bookReservationListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/bookreservationlist/bookReservationListMain.do")
	public String bookReservationListHandle(@RequestParam int lo) {

		return "bookReservationListMain";
	}
	/*=======================
	 * 분실도서신고내역
	 *=======================*/
	@RequestMapping("/lm/mypage/booklostlist/bookLostListMain.do")
	public String bookLostList() {
		return "bookLostListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/booklostlist/bookLostListMain.do")
	public String bookLostListHandle(@RequestParam int lo) {

		return "bookLostListMain";
	}
	/*=======================
	 * 이벤트참여내역
	 *=======================*/
	@RequestMapping("/lm/mypage/eventparticipatelist/eventParticipateListMain.do")
	public String eventParticipateList() {
		return "eventParticipateListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/eventparticipatelist/eventParticipateListMain.do")
	public String eventParticipateListHandle(@RequestParam int lo) {

		return "eventParticipateListMain";
	}
	/*=======================
	 * 중고서적등록내역
	 *=======================*/
	@RequestMapping("/lm/mypage/usedbookapplylist/usedBookApplyListMain.do")
	public String usedBookApplyList() {
		return "usedBookApplyListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/usedbookapplylist/usedBookApplyListMain.do")
	public String usedBookApplyListHandle(@RequestParam int lo) {

		return "usedBookApplyListMain";
	}
	/*=======================
	 * 찜한도서내역
	 *=======================*/
	@RequestMapping("/lm/mypage/zzimbooklist/zzimBookListMain.do")
	public String zzimBookList() {
		return "zzimBookListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/zzimbooklist/zzimBookListMain.do")
	public String zzimBookListHandle(@RequestParam int lo) {

		return "zzimBookListMain";
	}
	/*=======================
	 * 도서후기
	 *=======================*/
	@RequestMapping("/lm/mypage/bookwritelist/bookWriteListMain.do")
	public String bookWriteList() {
		return "bookWriteListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/bookwritelist/bookWriteListMain.do")
	public String bookWriteListHandle(@RequestParam int lo) {

		return "bookWriteListMain";
	}
	/*=======================
	 * 독후감작성
	 *=======================*/
	@RequestMapping("/lm/mypage/bookreportlist/bookReportListMain.do")
	public String bookReportList() {
		return "bookReportListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/bookreportlist/bookReportListMain.do")
	public String bookReportListHandle(@RequestParam int lo) {

		return "bookReportListMain";
	}
	/*=======================
	 * 보유쿠폰
	 *=======================*/
	@RequestMapping("/lm/mypage/couponlist/couponListMain.do")
	public String couponList() {
		return "couponListMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/couponlist/couponListMain.do")
	public String couponListHandle(@RequestParam int lo) {

		
		return "couponListMain";
	}
	/*=======================
	 * 회원정보수정
	 *=======================*/
	//수정 폼 호출
	@RequestMapping("/lm/mypage/myedit/myEditMain.do")
	public String myEdit(@Valid MyPageVO mypageVO,HttpServletRequest request,@RequestParam int lo,Model model) {
		HttpSession session = request.getSession();
		//로그인 여부 체크
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		//로그인 안되어있을 시 로그인 화면으로 이동
		if(mem_num==null) {
			model.addAttribute("accessMsg", "먼저 로그인을 해주세요.");
			if(lo==1) {
				return "common/notice_bs";
			}else {
				return "common/notice_lib";
			}
		}
		//name,email,cell 가져오기
		mypageVO = mypageService.getMyEdit(mem_num);
		model.addAttribute("mypageVO", mypageVO);
		
		return "myEditMain"; //타일스 설정의 식별자
	}
	@PostMapping("/lm/mypage/myedit/myEditMain.do")
	public String myEditHandle(@Valid MyPageVO mypageVO,@RequestParam int lo,BindingResult result, Model model,HttpServletRequest request) {
		log.debug("<<회원정보수정>> : " + mypageVO);
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "myEditMain";
		}
		//mem_num 가져오기
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		//VO에 mem_num 등록
		mypageVO.setMem_num(mem_num);
		//비밀번호가 변경 시도 여부 체크
		if(!mypageVO.getMem_new_passwd().equals("")) {
			//저장되어있는 salt 값 가져오기
			String salt = mypageService.getSalt(mem_num);
			//입력받은 비밀번호 암호화 (salt + mem_passwd)
			String mem_new_passwd = EncryptionPasswd.encryptionPasswd(salt,mypageVO.getMem_new_passwd());
			//VO에 SHA-256 passwd로 업데이트
			mypageVO.setMem_new_passwd(mem_new_passwd);
		}
		//입력된 회원정보만 수정
		if(!mypageVO.getMem_new_passwd().equals("")) {
			mypageService.updatePasswd(mypageVO);
			model.addAttribute("accessMsg", "비밀번호 수정 완료.");
		}
		if(!mypageVO.getMem_new_email().equals("")) {
			mypageService.updateEmail(mypageVO);
			model.addAttribute("accessMsg", "이메일 수정 완료.");
		}
		if(!mypageVO.getMem_new_cell().equals("")) {
			mypageService.updateCell(mypageVO);
			//이메일 변경 시 미인증이므로 auth=4 변경
			mypageService.updateAuth(mypageVO);
			model.addAttribute("accessMsg", "전화번호 수정 완료.");
		}
		model.addAttribute("lo",lo);
		return "common/notice_edit";
	}
	/*=======================
	 * 사용가능포인트정보
	 *=======================*/
	@RequestMapping("/lm/mypage/pointinfo/pointInfoMain.do")
	public String pointInfo() {
		return "pointInfoMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/pointinfo/pointInfoMain.do")
	public String pointInfoHandle(@RequestParam int lo) {

		return "pointInfoMain";
	}
	/*=======================
	 * 등급정보
	 *=======================*/
	@RequestMapping("/lm/mypage/gradeinfo/gradeInfoMain.do")
	public String gradeInfo() {
		return "gradeInfoMain"; //타일스 설정의 식별자
	}
	@GetMapping("/lm/mypage/gradeinfo/gradeInfoMain.do")
	public String gradeInfoHandle(@RequestParam int lo,Model model,HttpSession session,MyPageVO mypageVO) {
		log.debug("<<등급정보>> : " + mypageVO);
		//로그인 여부 체크
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		//로그인 안되어있을 시 로그인 화면으로 이동
		if(mem_num==null) {
			model.addAttribute("accessMsg", "먼저 로그인을 해주세요.");
			if(lo==1) {
				return "common/notice_bs";
			}else {
				return "common/notice_lib";
			}
		}

		//회원의 총 주문 금액
		int mem_order_price=0;
		//회원의 등급 점수
		int mem_grade_point=0;
		if(mypageService.getOrderPrice(mem_num) != null) { //int형은 null이 불가능하여 String으로 받은 후 int로 저장
			mem_order_price = Integer.parseInt(mypageService.getOrderPrice(mem_num));
			mem_grade_point = mem_order_price/100;
		}
		//천단위 , 처리
		String mem_order_price_str = (mem_order_price+"").replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",")+" 원";
		if(mem_order_price_str.equals("0 원")) {
			mem_order_price_str = "구매 내역이 없습니다.";
		}
		model.addAttribute("mem_order_price_str",mem_order_price_str);
		model.addAttribute("mem_grade_point",mem_grade_point);
		
		return "gradeInfoMain";
	}
	/*=======================
	 * 회원 탈퇴
	 *=======================*/
	@GetMapping("/lm/mypage/memberout/memberOutMain.do")
	public String memberOut(MyPageVO mypageVO,HttpServletRequest request,Model model,@RequestParam int lo) {
		HttpSession session = request.getSession();
		//로그인 여부 체크
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		//로그인 안되어있을 시 로그인 화면으로 이동
		if(mem_num==null) {
			model.addAttribute("accessMsg", "먼저 로그인을 해주세요.");
			if(lo==1) {
				return "common/notice_bs";
			}else {
				return "common/notice_lib";
			}
		}
		return "memberOutMain";
	}
	@PostMapping("/lm/mypage/memberout/memberOutMain.do")
	public String memberOutHandle(@RequestParam int lo,MyPageVO mypageVO,HttpServletRequest request,Model model,BindingResult result) {
		log.debug("<<회원탈퇴>> : " + mypageVO);
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");

		//저장되어있는 salt 값 가져오기
		String salt = mypageService.getSalt(mem_num);
		//입력받은 비밀번호 암호화 (salt + mem_passwd)
		String mem_passwd = EncryptionPasswd.encryptionPasswd(salt,mypageVO.getMem_passwd());
		//VO에 SHA-256 passwd로 업데이트
		mypageVO.setMem_passwd(mem_passwd);
		//입력받은 비밀번호, 아이디 체크 후 탈퇴
		if(mypageService.memberOutCheck(mypageVO) == mem_num) {
			//회원정보 삭제
			mypageService.memberOut(mem_num);
			//로그아웃
			session.invalidate();
		}
		model.addAttribute("accessMsg", "회원 탈퇴 완료.");
		if(lo==1) {
			return "common/notice_lib";
		}else {
			return "common/notice_bs";
		}
	}
}

