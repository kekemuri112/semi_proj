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
		<textarea rows="3" cols="3
		
		
		0" id="comments"></textarea><br>
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
					div.innerHTML="<strong>"+users_id +" : "+comments_content+"</strong>"
						        +"<input type='button' value='수정' ><input type='button' value='삭제'>";
					div.className="comments"
					commList.appendChild(div);
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
					div.innerHTML+=		
						"<a href='javascript:getList("+j+")'>["+j+"]</a>"
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

</script>