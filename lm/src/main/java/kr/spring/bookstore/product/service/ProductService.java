package kr.spring.bookstore.product.service;

import java.util.List;
import java.util.Map;

import kr.spring.bookstore.product.vo.ProductFavVO;
import kr.spring.bookstore.product.vo.ProductVO;
import kr.spring.bookstore.used.vo.UsedVO;

public interface ProductService {
	//상품리스트
	public List<ProductVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);	
	//상품 번호로 중고 상품 번호 리스트
	public List<UsedVO> selectUsedNum(int store_product_num);	
	//상품 상세
	public ProductVO selectProduct(String store_product_isbn13);	
	
	//좋아요
	public ProductFavVO selectFav(ProductFavVO fav);
	public int selectFavCount(Integer store_product_num);
	public void insertFav(ProductFavVO fav);
	public void deleteFav(Integer ZZIM_NUM);
}
