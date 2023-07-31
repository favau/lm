<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
 .find-books {
 	background-color: #f0f0f0;
    
 }
</style>
<script type="text/javascript">
	$(function(){
		$('.find-books').prop('disabled', true);
	});
</script>

<div class="used-contents">
	<span>책 등록하기</span>
	<form:form modelAttribute="usedVO" action="used/usedForm.do" id="used_form">
		<ul>
			<li>
				<!-- 책 찾기 -->
				<form:label path="store_product_title" >책 이름</form:label>
				<form:input path="store_product_title" class="find-books"/>
				<input type="button" onclick="execSearchProductcode()" value="책 찾기" class="used-default-btn">
				<span id="message_product_name"></span>
				<input type="hidden" id="hidden_store_product_num" value="${store_product_num}">
				<form:errors element="div" cssClass="error-color"/>
			</li>
			<li>
				<div>책 상태 선택</div>
				<select name="used_product_condition">
					<option value="1">하</option>
					<option value="2">중</option>
					<option value="3">상</option>
				</select>
				
			</li>
			<li>
				<!-- 책 사진1 -->
				<form:label path="used_product_photo1" >책 사진 1</form:label>
				<form:input path="used_product_photo1" />
				<span id="message_used_photo1"></span>
				<form:errors element="div" cssClass="error-color"/>
			</li>
			<li>
				<!-- 책 사진2 -->
				<form:label path="used_product_photo2" >책 사진 2</form:label>
				<form:input path="used_product_photo2" />
				<span id="message_used_photo2"></span>
				<form:errors element="div" cssClass="error-color"/>
			</li>
			<li>
				<!-- 책 설명 -->
				<form:label path="used_product_info" >책 설명</form:label>
				<form:textarea path="used_product_info" />
				<span id="message_used_product_info"></span>
				<form:errors element="div" cssClass="error-color"/>
			</li>
			<li>
				<!-- 기존 책 찾기 -->
				<form:label path="used_product_price" >판매 희망 가격</form:label>
				<form:input path="used_product_price" />
				<span id="message_used_product_price"></span>
				<form:errors element="div" cssClass="error-color"/>
			</li>
			<form:button class="used-default-btn">제출하기</form:button>
		</ul>
	</form:form>
	
</div>
	<script type="text/javascript">
		function execSearchProductcode() {
			
			var contextPath = "${pageContext.request.contextPath}";
			var popupUrl = contextPath + "/bookstore/used/usedSearchProductPopup.do";
			var child = window.open(popupUrl, "_blank", "width=500, height=500");

		};
	</script>