<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="approval">

</div>
<div id="page">

</div>
<script type="text/javascript">
	var cp='<c:out value="${cp}"/>';
	window.onload=function(){
		getList();
		getPage();
	}
	
	function getList(pageNum){
		var xhr=new XMLHttpRequest();
		var pageNum1=1;
		if(pageNum!=null){
			pageNum1=pageNum;
		}
		xhr.onreadystatechange = function(){
			if(xhr.readyState==4&&xhr.status==200){
				deleteDiv();
				var data=xhr.responseText;
				var json=JSON.parse(data); //json 배열로 받아옴.
				var approval=document.getElementById("approval"); //div 가져옴
				var table=document.createElement("table"); //테이블 태그 생성
				table.border="1"; //테이블 테두리지정.
				var ths=["회원아이디","회원이름","회원생일","전화번호","활동포인트","현재상태","비고"];//맨위행
				var tr=document.createElement("tr"); //행만들기
				for(var i=0;i<7;i++){ // 6번째열까지 반복
					var th=document.createElement("th");
					var text=document.createTextNode(ths[i]); 
					th.appendChild(text); // th에 텍스트값 추가
					tr.appendChild(th); // tr에 th자식으로 추가
				}
				table.appendChild(tr); // 테이블에 tr추가. (행 append)
				var cafe_numList=new Array; //어느행에서 버튼을 눌렀는지 구분하기위해 pk를 리스트에 담아줌
				for(var i=0;i<json.length-1;i++){		
					const z=i;
					var tr1=document.createElement("tr");
					var users_num=json[i].users_num;
					var users_id=json[i].users_id;
					var users_name=json[i].users_name;
					var users_birth=json[i].users_birth;
					var users_phone=json[i].users_phone;
					var users_cafe_num=json[i].users_cafe_num;
					var cafe_num=json[i].cafe_num;
					var users_cafe_approved=json[i].users_cafe_approved;
					var users_cafe_point=json[i].users_cafe_point
					var pageNum=json[json.length-1].pageNum;
					cafe_numList[z]=users_cafe_num; //리스트에 pk담음
					var tds=[users_id,users_name,users_birth,users_phone,users_cafe_point,users_cafe_approved]
					for(var j=0;j<7;j++){
						var td=document.createElement("td");
						if(j==1){  // 2번째칸 이름출력시 하이퍼링크검  
							
							var aTag=document.createElement("a");
							aTag.href="javascript:viewAnswer("+cafe_numList[z]+","+pageNum+")";
							var text=document.createTextNode(tds[1])
							aTag.appendChild(text);
							td.appendChild(aTag);
						}else if(j==6){ //맨마지막열일때
							if(tds[5]==("승인")){
								const z=i;
								var button1=document.createElement("input");
								button1.type="button"; // input 타입 button 버튼생성
								button1.value="추방"; // 버튼 value값
								button1.addEventListener('click', function() { 
									redCard(cafe_numList[z],pageNum);
								}) //
								td.appendChild(button1);
							}else{
								const z=i;
								var button1=document.createElement("input");
								var button2=document.createElement("input");
								button1.type="button"; // input 타입 button 버튼생성
								button2.type="button";
								button1.value="승인"; // 버튼 value값
								button2.value="거절";
								//버튼클릭시 어느함수실행할지 지정.
								button1.addEventListener('click', function() { 
									accept(cafe_numList[z],pageNum)
								}) //
								button2.addEventListener('click', function() { 
									redCard(cafe_numList[z],pageNum);
								}) //
								td.appendChild(button1);  // 마지막열에 버튼추가.
								td.appendChild(button2);
							}
						}else{ //나머지열  
							var text=document.createTextNode(tds[j])
							td.appendChild(text); //열에 텍스트추가
						}
						tr1.appendChild(td); //열을 행에추가
					}
					table.appendChild(tr1); //행을 테이블에 추가
				}
				approval.appendChild(table); //테이블을 div에 추가
			}
		}
		xhr.open('post',cp+'/cafe/usersapproval.do',true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.send('pageNum='+pageNum1);
	}
	function viewAnswer(users_cafe_num){
		var url=cp+"/cafe/answerPopup.jsp?users_cafe_num="+users_cafe_num;
		var name="-Answer-";
		var option="width=500,height=500"
		window.open(url,name,option);
	}
	function redCard(users_cafe_num,pageNum){ // 강퇴기능.
		console.log(users_cafe_num)
		if(confirm("추방하시겠습니까?")){
			var xhr=new XMLHttpRequest();
			xhr.onreadystatechange=function(){
				if(xhr.status==200&&xhr.readyState==4){
					var data=xhr.responseText;
					var json=JSON.parse(data);
					if(json.result){
						alert("추방성공")
						paging();
					}else{
						alert("추방실패")
					}
				}
			}
			xhr.open('post',cp+'/cafe/redcard.do',true);
			xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			xhr.send('users_cafe_num='+users_cafe_num);
		}	
	}
	function accept(users_cafe_num,pageNum){ // 상태를 대기---> 승인으로.
		console.log(users_cafe_num);
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200&&xhr.readyState==4){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				if(json.result){
					alert("승인성공");
					paging();
				}
			}
		}
		xhr.open('post',cp+'/cafe/accept.do',true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.send('users_cafe_num='+users_cafe_num);
	}
	function alert1(users_cafe_num){ //테스트용 알람창
		console.log(users_cafe_num);
		alert("테스트!!! : "+users_cafe_num);
	}
	function getPage(pageNum){ //페이지처리
		var pageNum1=1;
		if(pageNum!=null){
			pageNum1=pageNum;
		}
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200&&xhr.readyState==4){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				var i=json.length-1;
				var startPage=json[i].startPage;
				var endPage=json[i].endPage;
				var pageCount=json[i].pageCount;
				var pageNum=json[i].pageNum;
				console.log(endPage);
				var div=document.getElementById("page");		
				if(startPage>5){
					div.innerHTML+="<a href='javascript:paging("+(startPage-1)+")'>[이전]</a>"
				}
				for(var j=startPage;j<=endPage;j++){
					div.innerHTML+="<a href='javascript:getList("+j+")'>["+j+"]</a>"
				}
				if(pageCount>endPage){
					div.innerHTML+="<a href='javascript:paging("+(endPage+1)+")'>[다음]</a>";			
				}
			}
		}
		xhr.open('post',cp+'/cafe/usersapproval.do',true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.send('pageNum='+pageNum1);
	}
	function deleteDiv(){ //리스트 div 초기화
		var commList=document.getElementById("approval");
		var chiled=commList.childNodes;
		for(var i=chiled.length-1;i>=0;i--){
			var comments=chiled.item(i);
			commList.removeChild(comments);
		}
	}
	
	function paging(pageNum) {
		getList(pageNum);
		document.getElementById("page").innerHTML="";
		getPage(pageNum);
	}
</script>

