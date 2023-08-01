package kr.spring.bookstore.service.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.bookstore.service.vo.AnnounceVO;
import kr.spring.bookstore.service.vo.FaqVO;

@Mapper
public interface ServiceMapper {
	//공지사항
	public List<AnnounceVO> selectAnnounceList(Map<String, Object> map);
	@Select("SELECT * FROM lm_board_announce WHERE board_num=#{board_num}")
	public AnnounceVO selectAnnounce(Integer board_num);
	@Insert("INSERT INTO lm_board_announce (board_num,board_title,board_content,board_reg_date,board_filename) VALUES (board_announce_seq.nextval,#{board_title},#{board_content},SYSDATE,#{board_filename})")
	public void insertAnnounce(AnnounceVO announceVO);
	public int selectRowCount(Map<String,Object> map);
	
	//자주묻는질문
	//작성
	@Insert("INSERT INTO lm_board_faq (faq_num,faq_title,faq_content,faq_reg_date,faq_category) VALUES (lm_board_faq_seq.nextval,#{faq_title},#{faq_content},SYSDATE,#{faq_category})")
	public void insertFaq(FaqVO faqVO);
	//목록불러오기/검색
	public List<FaqVO> selectFaqList(Map<String, Object> map);
	public int selectRowCountFaq(Map<String,Object> map);
}