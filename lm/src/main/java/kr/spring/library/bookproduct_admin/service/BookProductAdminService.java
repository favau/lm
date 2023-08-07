package kr.spring.library.bookproduct_admin.service;

import java.util.List;
import java.util.Map;

import kr.spring.library.product.vo.BookProductVO;

public interface BookProductAdminService {
	// 도서 상세 정보
	public BookProductVO selectDetailBookProduct(String callNumber);
	// 전체 도서 목록
	public List<BookProductVO> selectBookProductList(Map<String, Object> map);
	// 도서 개수 (xml)
	public int selectRowCount(Map<String, Object> map);
	// 대출상태 변경(0:대출가능 1:대출중) == 권한수정
	public void updateByBookProductAdmin(BookProductVO bookProductVO);
	// 대출중인 도서 리스트
	public List<BookProductVO> selectListLoan (int lib_product_product_status);
	// 대출 도서 목록
	public List<BookProductVO> selectLoanList(Map<String, Object> map);
	// 대출횟수 증가(누적) status값이 바뀌면 누적되게 해야함..
	public void updateLoanCnt(Integer lib_product_loanCnt);
}
