<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<%
	String scontents_num=request.getParameter("contents_num");
	int contents_num=Integer.parseInt(scontents_num);
	int users_num=Integer.parseInt(request.getParameter("users_num"));
%>
<div id="commList">
</div>
<div id="page">
</div><br>
	<div>
		<textarea rows="3" cols="30" id="comments"></textarea><br>
		<input type="button" value="등록하기" onclick="comments_write()">
	</div>
<script type="text/javascript">
	window.onload=function(){
		getList();
		page();
	}
	function getList(pageNum){ //리스트출력
		console.log("list 함수 실행!!");
		console.log("pageNum : "+pageNum)
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				deleteDiv();
				var data=xhr.responseText;
				var json=JSON.parse(data);
				var commList=document.getElementById("commList");
				for(var i=0;i<json.length-1;i++){
					var users_id=json[i].users_id;
					var comments_content=json[i].comments_content;
					var comments_num=json[i].comments_num;
					var div=document.createElement("div");
					var pageNum=json[json.length-1].pageNum
					var comments_lev=json[i].comments_lev;
					const j=i;
					if(comments_lev>0){
						var span=document.createElement("span");
						for(var z=1;z<=comments_lev;z++){
							span.innerHTML="&nbps;"
						}
						span.innerHTML+="ㄴ";
					}
					div.innerHTML="<strong>"+users_id +" :"+comments_content+"</strong>"
								+"<input type='hidden' value='"+users_id+"'>"
								+"<input type='hidden' value='"+comments_content+"'>"
								+"<input type='hidden' value='"+comments_num+"'>"
								+"<input type='hidden' value='"+pageNum+"'>"
								+"<input type='button' value='답글' onclick='comments_reply("+j+")'>"
						        +"<input type='button' value='수정' onclick='modify("+j+")'>"
								+"<input type='button' value='삭제' onclick='comments_delete("+j+")'>";
					div.id="comment"+j;
					div.className="comments"
					commList.appendChild(div);	
					var div2=document.createElement("div");
					div2.innerHTML="<textarea rows='3' cols='30' id='reply_value'"+j+"></textarea><br>"
								 +"<input type='button' value='확인' onclick='reply_insert("+comments_num+","+j+")'>"
								 +"<input type='button' value='취소' onclick=''>";
					div2.style="display:none";
					div2.id="comments_re"+j;
					div.appendChild(div2);
				}
			}
		}
		var pageNum1=1;
		if(pageNum!=null){
			pageNum1=pageNum;
		}
		console.log("pageNum1 :"+pageNum1);
		xhr.open('get', '${cp}/comments/comments.do?contents_num=<%=contents_num%>&users_num=<%=users_num%>&pageNum='+pageNum1, true);
		xhr.send();
	}
	function reply_insert(comments_num,j){
		var text_area_reply=document.getElementById("reply_value"+j);
		var replay_value=text_area_reply.value;
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			
		}
		xhr.open('post','',true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.send();
		
	}
	function comments_reply(j){ // 숨겨진 div 보여지게하기
		var div=document.getElementById("comments_re"+j);
		div.style="display:block";
	}
	function comments_write(){ //댓글쓰기
		console.log("쓰기 함수 호출!!!!");
		console.log("contents_num : "+<%=contents_num%>);
		var textarea_comments_content=document.getElementById("comments")
		var comments_content=textarea_comments_content.value;
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200&xhr.readyState==4){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				if(json.using){
					paging();
				}else{
					alert("댓글작성에러발생!!");
					paging();
				}
				var textarea_comments_content=document.getElementById("comments");
				textarea_comments_content.value="";
			}			
		}
		xhr.open('post','${cp}/comments/insert.do',true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.send("contents_num=<%=contents_num%>&comments_content="+comments_content);
	}
	function page(pageNum){ //페이지처리
		console.log("page함수 호출!!!");
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
				console.log(startPage);
				var div=document.getElementById("page");		
				if(startPage>5){
					div.innerHTML+=			
						"<a href='javascript:paging("+(startPage-1)+")'>[이전]</a>"
				}
				for(var j=startPage;j<=endPage;j++){
					console.log("j:"+j);
					if(j==pageNum){
						div.innerHTML+=		
							"<a href='javascript:getList("+j+")'><span style='color'>["+j+"]</span></a>"
					}else{
						div.innerHTML+=		
							"<a href='javascript:getList("+j+")'>["+j+"]</a>"
					}	
				}
				if(pageCount>endPage){
					div.innerHTML+=
						"<a href='javascript:paging("+(endPage+1)+")'>[다음]</a>";			
				}
			}
		}
		xhr.open('get','${cp}/comments/comments.do?contents_num=<%=contents_num%>&users_num=<%=users_num%>&pageNum='+pageNum1,true);
		xhr.send();
	}
	function deleteDiv(){
		var commList=document.getElementById("commList");
		var chiled=commList.childNodes;
		for(var i=chiled.length-1;i>=0;i--){
			var comments=chiled.item(i);
			commList.removeChild(comments);
		}
	}
	function paging(pageNum){
		var div=document.getElementById("page");
		div.innerHTML=" "
		getList(pageNum);
		page(pageNum);
	}
	function modify(j){
		var div=document.getElementById("comment"+j);
		div.style.border="3px solid salmon";
		var com=div.childNodes;
		var users_id=com[1].value;
		var comments_content=com[2].value;
		var comments_num=com[3].value;
		var pageNum=com[4].value;
		div.innerHTML=users_id+"<input type='text' value='"+comments_content+"'>"
					 +"<input type='hidden' value='"+users_id+"'>"
					 +"<input type='hidden' value='"+comments_content+"'>"
					 +"<input type='hidden' value='"+comments_num+"'>"
					 +"<input type='hidden' value='"+pageNum+"'>"
					 +"<input type='button' value='확인' onclick='update_comment("+j+")'>"
					 +"<input type='button' value='취소' onclick='return_comment("+j+")'>";
	}
	function return_comment(j){
		var div=document.getElementById("comment"+j);
		var com=div.childNodes;
		var pageNum=com[5].value;
		paging(pageNum);
		
	}
	function update_comment(j){
		var div=document.getElementById("comment"+j);
		var com=div.childNodes;
		var users_id=com[2].value;
		console.log("update 함수의 user_id : "+users_id)
		var comments_content=com[1].value;
		console.log("update 함수의 comments_content : "+comments_content)
		var comments_num=com[4].value
		var pageNum=com[5].value
		console.log("update 함수의 comments_num : "+comments_num)
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200&xhr.readyState==4){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				if(json.result){
					paging(pageNum);
				}else{
					alert("댓글수정을 실패하였습니다");
					paging(pageNum);
				}
			}
		}
		xhr.open('post','${cp}/comments/update.do',true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.send("users_id="+users_id+"&comments_content="+comments_content+"&comments_num="+comments_num);
	}
	function comments_delete(j){
		var div=document.getElementById("comment"+j);
		var com=div.childNodes;
		var comments_num=com[3].value;
		var pageNum=com[4].value;
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200&xhr.readyState==4){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				if(json.result){
					paging(pageNum);
				}else{
					alert("삭제실패!!")
					paging(pageNum);
				}
				
			}
		}
		xhr.open('post','${cp}/comments/delete.do',true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.send('comments_num='+comments_num);
		
	}
</script>
