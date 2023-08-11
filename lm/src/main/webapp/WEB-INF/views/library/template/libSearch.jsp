<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="used-contents">
	<c:if test="${resultSearch != ''}">
		<span>"${resultSearch}"에 대한 검색 결과</span>
	</c:if>
	
	<div class="flex-end-contents">
		<ul>
			<li><a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=${selectedCategoryNum}&orderByNum=1&keyword=${resultSearch}">날짜 순 |&nbsp;</a></li>
			<li><a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=${selectedCategoryNum}&orderByNum=2&keyword=${resultSearch}">제목 순 |&nbsp;</a></li>
			<li><a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=${selectedCategoryNum}&orderByNum=3&keyword=${resultSearch}">저자 순 |&nbsp;</a></li>
			<li><a href="${pageContext.request.contextPath}/library/template/libSearchMain.do?categoryNum=${selectedCategoryNum}&orderByNum=4&keyword=${resultSearch}">출판사 순</a></li>
		</ul>
	</div>
	<div class="flex-start-box">책 갯수 : ${totalCount}</div>
	<div class="search-box-byUsed">
		<c:forEach var="list" items="${list}">
			<div class="used-all-contents-div-width">
				<div class="used-all-contents-img">
					<a href="${pageContext.request.contextPath}/library/lib_book/bookDetail.do?callNumber=${list.callNumber}"><img src="${list.lib_product_bookimageurl}"></a>
				</div>
				<div class="used-all-contents-column">
					<div class="used-all-contents-box">책 제목 : ${list.lib_product_bookname}</div>
					<div class="used-all-contents-box">출판 년도 : ${list.lib_product_publication_year} | 저자 : ${list.lib_product_authors} | 출판사 : ${list.lib_product_publisher}</div>
					<div class="used-all-contents-box-detail">책 내용 : ${list.lib_product_detail}</div>
					
				</div>
			</div>
		</c:forEach>
		<div class="paging">${page}</div>
	</div>
</div>