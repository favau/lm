$(function(){
	
	//이벤트 상태 읽기
	function selecStatus(){
		$.ajax({
			url: 'getStatus.do',
			type: 'post',
			data: {event_attendance_point_num:$('#event_attendance_point_num').val()},
			dataType: 'json',
			success: function(param){
				param.list
				displayStatus(param);
			},
			error: function(){
				alert('네트워크 오류발생');
			}
		});
	}
	//이벤트 도장 읽기}=
	function selectStamp(){
		let todayNum = 0;
		$.ajax({
			url: 'getStamp.do',
			type: 'post',
			data: {event_attendance_point_num:$('#event_attendance_point_num').val()},
			dataType: 'json',
			success: function(param){
				if(param.status == 'yesStamp'){
					param.list.forEach(function (item){
					let todate = new Date(item.event_attendance_date);
						todayNum = todate.getDate();
						displayStamp(todayNum);
					});	
				}else if(param.status == 'noStamp'){
					
				}else{
					alert('표시 오류 발생');
				}
				
				
			},
			error: function(){
				alert('네트워크 오류발생');
			}
		});
	}
	
	//표시 공통 함수
	function displayStatus(param){
		if(param.status == 'yesCount'){
			$('#attendance_count').text(param.attendance.attendance_count+' 개');
			
			if(param.attendance.point_get1==1){
				document.getElementById('point_get1').className = 'yesPoint';
			}
			if(param.attendance.point_get2==1){
				document.getElementById('point_get2').className = 'yesPoint';
			}
			if(param.attendance.point_get3==1){
				document.getElementById('point_get3').className = 'yesPoint';
			}
		}else if(param.status == 'noCount'){
			$('#attendance_count').text(' 개');
		}else{
			alert('표시 오류 발생');
		}
		//문서객체에 추가
	} //end of display
	
	//stamp표시 공통
	function displayStamp(todayNum){
		console.log(todayNum);
		let dayclass = '.day'+todayNum;
		document.querySelector(dayclass).classList.add('stampOn');
		/*let daycss = '.day'+todayNum+' .bgStamp';
		$(daycss).css('opacity','1');*/
	}
	
	//출석 체크 이벤트 클릭
	$('#attendanceCheck').click(function(){
		let currentMonth = now.getMonth() + 1;
		let currentDay = now.getDate();
		$.ajax({
			url: 'writeAttendance.do',
			type: 'post',
			data: {currentMonth:currentMonth, currentDay:currentDay },
			dataType: 'json',
			success: function(param){
				if(param.result == 'logout'){
					alert('출석체크는 로그인 후 가능합니다!');
				}else if(param.result == 'success'){
					displayStamp(currentDay);
					displayStatus(param);
				}else if(param.result == 'Check'){
					alert('오늘은 이미 출석을 완료 했습니다.');
				}
				else{
					alert('등록시 오류 발생');
				}
			},
			error: function(){
				alert('네트워크 오류발생');
			}
		});
	});
	selecStatus();
	selectStamp();
	
});