package kr.spring.mypage.service;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import kr.spring.mypage.vo.MyPageVO;

public interface MyPageService {
	//마이페이지 정보 가져오기
	//mem_grade,mem_point,zzim_num,review_num,rep_num,coupon_num
	public String getMemRegDate(int mem_num);
	public List<MyPageVO> getDetailData(int mem_num);
	public int getZzimNumCount(int mem_num);
	public int getReviewNumCount(int mem_num);
	public int getRepNumCount(int mem_num);
	public int getCouponNumCount(int mem_num);
	public String getOrderPrice(int mem_num);
	//내정보 수정
	public MyPageVO getMyEdit(int mem_num); 
	public String getSalt(int mem_num);
	public void updatePasswd(MyPageVO mypageVO);
	public void updateEmail(MyPageVO mypageVO);
	public void updateCell(MyPageVO mypageVO);
	public void updateAuth(MyPageVO mypageVO);
	public int memberOutCheck(MyPageVO mypageVO);
	public void memberOut(int mem_num);
	//회원정보수정 이미지 업로드
	public void updatePhoto(MyPageVO mypageVO);
	//이미지 유무 확인
	public MyPageVO getPhoto(int mem_num);
	//회원정보 수정일 수정
	public void updateModifyDate(MyPageVO mypageVO);
	//주문내역
	public List<MyPageVO> getOrderList(Map<String,Object> map);
	//개시글 개수
	public int selectRowCountOrderList(Map<String,Object> map);
	
	
}
