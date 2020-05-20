<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP Page</title>
</head>
<body onload="getMessages();">
<div id="content">

  <% if (application.getAttribute("messages") != null) {%>
  <%= application.getAttribute("messages")%>
  <% }%>
</div>
<div>
	<textarea id="msgbox" rows="10" cols="40" style="border:none; background-color: rgba(255,255,255,0); margin-left:29px;float: left;display: inline-block;" readonly="readonly"></textarea>
	<br><br>
	</div>
	<input style="border: none;width:300px;height:30px;" type="text" id="message">
	<br><br>
	<input type="button"  style=" width:70px;height:35px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" value="보내기" onclick="postMessage()">
<script>
    function postMessage() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("post", "${cp}/chat/shoutServlet.do?t="+new Date(), false);
        xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var users_id = escape('${users_id }');
        var messageText = escape(document.getElementById("message").value);
        document.getElementById("message").value = "";
        xmlhttp.send("name="+users_id+"&message="+messageText);
    }
    var messagesWaiting = false;
    function getMessages(){
        if(!messagesWaiting){
            messagesWaiting = true;
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange=function(){
                if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                    messagesWaiting = false;
                    var msgbox = document.getElementById("msgbox");
                    msgbox.value += xmlhttp.responseText;
                }
            }
            xmlhttp.open("get", "${cp}/chat/shoutServlet.do?t="+new Date(), true);
            xmlhttp.send();
        }
    }
    setInterval(getMessages, 1000);
</script>
</body>
</html>